package com.semester.tinder;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class TinderApplication {

	public static void main(String[] args) {

    ClassLoader classLoader =TinderApplication.class.getClassLoader();
		File file =new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());

try {
	FileInputStream serviceAccount =new FileInputStream(file.getAbsolutePath());

	// config connect for project:
	FirebaseOptions options = new FirebaseOptions.Builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount)) // config credentials contains data.
			.setStorageBucket("tinder-b4b61.appspot.com") // config storage contains files.
			.build();

	FirebaseApp.initializeApp(options);

}catch (Exception e){
throw new RuntimeException(e.getMessage());
}

		SpringApplication.run(TinderApplication.class, args);


	}

}
