public class Naga extends Musuh implements BisaTerbang, BisaLoot {

    public Naga() {
        super("Naga Hitam", 500);
    }

    @Override
    public void terimaDamage(int damage) {
        int damageMasuk = damage - 15;

        if (damageMasuk < 0) {
            damageMasuk = 0;
        }

        this.healthPoint -= damageMasuk;

        if (this.healthPoint < 0) {
            this.healthPoint = 0;
        }

        System.out.println(
                "Armor sisik " + this.namaMusuh +
                " menahan serangan! Hanya menerima " +
                damageMasuk + " damage. Sisa HP: " +
                this.healthPoint
        );
    }

    @Override
    public void suaraKhas() {
        System.out.println(this.namaMusuh + ": ROAAAARRRR!");
    }

    @Override
    public void lepasLandas() {
        System.out.println(
                this.namaMusuh +
                " terbang tinggi! Sulit diserang."
        );
    }

    @Override
    public void seranganUdara() {
        System.out.println(
                this.namaMusuh +
                " menyemburkan badai api! Player -80 HP."
        );
    }

    @Override
    public void jatuhkanItem() {
        System.out.println(
                this.namaMusuh +
                " menjatuhkan item langka: Sisik Naga Api!"
        );
    }
}