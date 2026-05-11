public class Slime extends Musuh implements BisaLoot {

    public Slime() {
        super("Slime Berlendir", 50);
    }

    @Override
    public void terimaDamage(int damage) {
        super.terimaDamage(damage);

        if (this.healthPoint > 0) {
            System.out.println("Efek pasif: " + this.namaMusuh +
                    " membelah diri menjadi dua!");
        }
    }

    @Override
    public void suaraKhas() {
        System.out.println(this.namaMusuh + ": Blub blub...");
    }

    @Override
    public void jatuhkanItem() {
        System.out.println(this.namaMusuh +
                " menjatuhkan Gel Slime!");
    }
}