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

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.worklight.common.WLTrusteer;
import com.worklight.wlclient.api.WLClient;
import com.worklight.wlclient.api.WLResourceRequest;

public class MainActivity extends Activity {
	private static TextView resultTextView = null;
	private static MainActivity _this;
	private static Button adapterBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		_this = this;

		adapterBtn = (Button)findViewById(R.id.adapterBtn);
		adapterBtn.setEnabled(false);
		
		final WLClient client = WLClient.createInstance(this);
		client.registerChallengeHandler(new TrusteerChallengeHandler());
		client.connect(new MyConnectListener());
		
		TextView intro = (TextView)findViewById(R.id.intro);
		if(WLTrusteer.hasTrusteerSDK()){
			intro.setText("Trusteer SDK Loaded!");
			WLTrusteer trusteer = WLTrusteer.getInstance();
			JSONObject risks = trusteer.getRiskAssessment();
			try {
				JSONObject rooted = (JSONObject) risks.get("os.rooted");
				if(rooted.getInt("value") > 0){
					intro.setText(intro.getText() + "\n... and it seems this device is rooted");
				}
				else{
					intro.setText(intro.getText() + "\n... and it seems this device is NOT rooted");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		resultTextView = (TextView)findViewById(R.id.resultTextView);
		
		
		adapterBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				appendResult("Invoking Procedure...");				
				URI adapterPath;
				try {
					adapterPath = new URI("/adapters/MyAdapter/getData");
					WLResourceRequest request = new WLResourceRequest(adapterPath,WLResourceRequest.GET);
					request.send(new MyInvokeListener());

				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
		});
	}
	
	public static void appendResult(final String str){
		Runnable run = new Runnable() {			
			public void run() {
				String newText = (String)resultTextView.getText() +'\n' + str;
				resultTextView.setText(newText);				
			}
		};
		_this.runOnUiThread(run);
	}
	
	public static void enableButton(){
		Runnable run = new Runnable() {			
			public void run() {
				adapterBtn.setEnabled(true);				
			}
		};
		_this.runOnUiThread(run);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
