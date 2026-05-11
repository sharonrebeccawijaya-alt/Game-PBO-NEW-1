public class Zombie extends Musuh implements BisaLoot {

    public Zombie() {
        super("Zombie Ganas", 150);
    }

    @Override
    public void serangPemain() {
        System.out.println(this.namaMusuh +
                " menggigit pemain! Player -20 HP");
    }

    @Override
    public void suaraKhas() {
        System.out.println(this.namaMusuh + ": Uwaaagh...");
    }

    @Override
    public void jatuhkanItem() {
        System.out.println(this.namaMusuh +
                " menjatuhkan Tulang Rapuh!");
    }
}