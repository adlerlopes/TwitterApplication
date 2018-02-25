package br.adler.twitterapplication;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public final class TwitterApplication {

	private Twitter twitter;
	private String word;

	private String[] words = { "LINDO", "GOSTOSO", "SENSACIONAL", "DELICIOSO", "SAFADO", "PROGRAMADOR", "DEDO DELICIOSO", "CARINHOSO", "SEU CRUSH", "CHAMA NA DM" };

	private long timeInterval;

	public static void main(String[] args) {
		new TwitterApplication().start();
	}

	public TwitterApplication() {
		String consumerKey = "Seu consumerKey";
		String consumerSecret = "Seu consumerSecret";

		String accessToken = "Seu accessToken";
		String accessTokenSecret = "Seu accessTokenSecret";

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret)
				.setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret);

		twitter = new TwitterFactory(cb.build()).getInstance();
	}

	public void start() {
		timeInterval = 10000;

		Runnable runnable = new Runnable() {

			public void run() {
				while (true) {
					try {
						word = words[(int) (Math.random() * words.length)];
						twitter.updateProfile("Adler / " + word, "www.adlerlopes.com.br", "Mato Grosso",
								"Programmer @ZenixCC, Contato: eu@adlerlopes.com.br");
						System.out.println("Alteração efetuada com sucesso.");
					} catch (TwitterException twitterException) {
						System.out.println("Erro ao efetuar a alteração.");
					}
					try {
						Thread.sleep(timeInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
	}
}