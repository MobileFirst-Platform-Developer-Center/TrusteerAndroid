IBM MobileFirst Platform Foundation
===
### Trusteer for Android


### Usage
Follow the instructions in the tutorial(s) listed below.

#### Trusteer version
This sample was designed and tested with Trusteer 3.6.
To use with with Trusteer 4.0:

- In *AndroidManifest.xml* add:
```
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
```

- Inside the `<application>` element:
```
<service android:name="com.trusteer.taz.service.TasService"/>


<receiver android:name="com.trusteer.taz.service.TasIntentReceiver" >
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED" />
    </intent-filter>
</receiver>
```
### Tutorials
https://developer.ibm.com/mobilefirstplatform/documentation/integration-7-1/trusteer-android-integration/

### Supported Levels
IBM MobileFirst Platform Foundation 7.1

### License
Copyright 2015 IBM Corp.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
