If you have stuff that are prone to crash & outside your development scope (like 3rd party SDKs) etc, its best to create a separate Service in remote process

```
<service android:name=".RemoteService" android:process=":remote">
</service>
```

This way - your remote service crash will not affect the main app. You can also log what parts are causing the crash (the 3rd party SDK might change in future leading to new crash points) & you could deploy a try catch in those blocks. 

You might also like to read up a bit on how your Application gets recreated with a remote process at [https://stackoverflow.com/questions/15545345/using-androidprocess-remote-recreates-android-application-object](https://stackoverflow.com/questions/15545345/using-androidprocess-remote-recreates-android-application-object)

This sample application is to help folks understand & getting started with remote process service in Android.
