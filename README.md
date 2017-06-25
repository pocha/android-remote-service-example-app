If you have stuff that are prone to crash & outside your development scope (like 3rd party SDKs) etc, its best to create a Service in a separate process

```
<service android:name=".RemoteService" android:process=":remote">
</service>
```

This way - your remote service crash will not affect the main app. You can also log what parts are causing the crash (the 3rd party SDK might change in future leading to new crash points) & you could deploy a try catch in those blocks. 

You might also like to read up a bit on how your Application gets recreated with a remote process at [https://stackoverflow.com/questions/15545345/using-androidprocess-remote-recreates-android-application-object](https://stackoverflow.com/questions/15545345/using-androidprocess-remote-recreates-android-application-object)

This sample application is to help folks understand & getting started with remote process service in Android.

Please note, the purpose of Remote Service IMHO is to expose services for other apps similar to Content Provider. This example let the Activity from the same app talk to the Service so this isnt exactly using any of the Remote Service things.

To use the app, import the project in Android Studio & run RemoteServiceClient.java from inside of RemoteService directory. There is an exception being thrown inside the Service & as soon as the Service is started, it will crash. You can filter the logs with 'Ashish' keyword to check the Service specific things. 

```
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		serviceHandler = new Handler();
		serviceHandler.postDelayed(myTask, 1000L);
		Log.d(getClass().getSimpleName(), "onStart()");
	}
	
	class Task implements Runnable {
		public void run() {
			++counter;
			serviceHandler.postDelayed(this,1000L);
			Log.i(getClass().getSimpleName(), "Incrementing counter in the run method");
			int i = 100/0; //this should throw exception in service
		}
	}
```
