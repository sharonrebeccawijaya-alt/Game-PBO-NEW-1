public class Musuh {
    protected String namaMusuh;
    protected int healthPoint;

    // constructor default
    public Musuh() {
        this.namaMusuh = "Monster Dasar";
        this.healthPoint = 100;
    }

    // constructor parameter
    public Musuh(String namaMusuh, int healthPoint) {
        this.namaMusuh = namaMusuh;
        this.healthPoint = healthPoint;
    }

    public void serangPemain() {
        System.out.println(this.namaMusuh +
                " melakukan serangan fisik! Player kehilangan 10 HP");
    }

    public void suaraKhas() {
        System.out.println(this.namaMusuh +
                " mengeluarkan suara menyeramkan!");
    }

    public void terimaDamage(int damage) {
        this.healthPoint -= damage;

        if (this.healthPoint < 0) {
            this.healthPoint = 0;
        }

        System.out.println(this.namaMusuh +
                " terkena serangan! Sisa HP: " + this.healthPoint);
    }
}