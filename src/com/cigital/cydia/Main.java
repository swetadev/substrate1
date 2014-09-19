package com.cigital.cydia;
import java.lang.reflect.Method;

import android.util.Log;

import com.saurik.substrate.MS;
import com.saurik.substrate.MS.ClassLoadHook;

public class Main {
	
	static void initialize() {
		 Log.i("Cigital", "Substrate Initialized.");
		
		ClassLoadHook hook = new MS.ClassLoadHook() {
			
			@Override
			public void classLoaded(Class<?> _class) {
				 Method method;


	              final String methodName = "validatePassword";
	              Log.i("Cigital", "Class Loaded.");
	              try{
	                  method = _class.getMethod(methodName, String.class, String.class);
	                 }catch(NoSuchMethodException e){
	                  method = null;

	                  Log.i("Cigital", "No such method: " + methodName);

	                 }
	              if (method != null) {
	              Log.i("Cigital", "Method Hooked.");
	              Log.i("Cigital", methodName);
	             // Log.i("Cigital", String.class.toString());
	              MS.hookMethod(_class, method, new MS.MethodAlteration<Object, Boolean>() {

					@Override
					public Boolean invoked(Object arg0, Object... arg1)
							throws Throwable {
						 Log.i("Cigital", (String)arg1[0]);
						 arg1[0]=arg1[1];
						 return invoke(arg0, arg1); // Call original 
						//return null;
					}
	              });
	              }
	              				
			}
		};
		MS.hookClassLoad("com.example.cigital.MainActivity", hook);
		
	
	}

}
