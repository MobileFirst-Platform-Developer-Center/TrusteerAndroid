/**
* Copyright 2015 IBM Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.worklight.trusteerandroid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.worklight.common.Logger;
import com.worklight.wlclient.api.challengehandler.WLChallengeHandler;

public class TrusteerChallengeHandler extends WLChallengeHandler {
	private static Logger logger = Logger.getInstance(TrusteerChallengeHandler.class.getSimpleName());
	
	public TrusteerChallengeHandler() {
		super("wl_basicTrusteerFraudDetectionRealm");
	}

	@Override
	public void handleFailure(JSONObject response) {
		try {
			MainActivity.appendResult("Senstitive data could not be retrieved because: " + response.getString("reason"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void handleSuccess(JSONObject response) {
		try {
			JSONArray alerts = response.getJSONObject("attributes").getJSONArray("alerts");
			if(alerts.length() > 0) {
				//logger.warn("TrusteerChallengeHandler.handleSuccess with alerts: " + alerts);
				MainActivity.appendResult("Please note that your device is :" + alerts);
			}
		}
		catch (Exception e) {
				logger.error("Unexpected error: " + e);
		}
		
	}

	@Override
	public void handleChallenge(JSONObject response) {
		// TODO Auto-generated method stub
		
	}

}
