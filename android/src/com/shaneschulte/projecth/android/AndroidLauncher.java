package com.shaneschulte.projecth.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.parse.ParseObject;
import com.shaneschulte.projecth.ProjectH;

import com.parse.Parse;

public class AndroidLauncher extends AndroidApplication {
    private final String clientKeyA = "wMO59oRluIVwH0MtU5AKofKubqo0qgirZRQCh2bF";
    private final String clientKeyB = "kQESlq43himmuPUavP6FLeioCFd4pC60oVu6FIWs";

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        //Initializes connection to parse,
        Parse.initialize(this, clientKeyA, clientKeyB);

        //Initializes android configurations
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new ProjectH(), config);
	}
}
