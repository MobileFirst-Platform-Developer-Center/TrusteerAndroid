/**
 * COPYRIGHT LICENSE: This information contains sample code provided in source code form. You may copy, modify, and distribute
 * these sample programs in any form without payment to IBM® for the purposes of developing, using, marketing or distributing
 * application programs conforming to the application programming interface for the operating platform for which the sample code is written.
 * Notwithstanding anything to the contrary, IBM PROVIDES THE SAMPLE SOURCE CODE ON AN "AS IS" BASIS AND IBM DISCLAIMS ALL WARRANTIES,
 * EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, ANY IMPLIED WARRANTIES OR CONDITIONS OF MERCHANTABILITY, SATISFACTORY QUALITY,
 * FITNESS FOR A PARTICULAR PURPOSE, TITLE, AND ANY WARRANTY OR CONDITION OF NON-INFRINGEMENT. IBM SHALL NOT BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF THE SAMPLE SOURCE CODE.
 * IBM HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS OR MODIFICATIONS TO THE SAMPLE SOURCE CODE.
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
