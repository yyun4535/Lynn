package ServerMain;

public class Word extends WordCenter {

	Word(int x, int y, String name) {
		super(x, y, "./img/" + name + ".png");
		autoPoint();

	}

	private void autoPoint() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (getY() < 400) {
					try {
						Thread.sleep(speed);
						setY(getY() + 5);
						System.out.println("name y : " + getImgName()+" : "+ getY());

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
