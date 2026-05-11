import java.util.Scanner;

public class ArenaPertarungan {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Musuh[] gelombangMonster = new Musuh[4];

        gelombangMonster[0] = new Slime();
        gelombangMonster[1] = new Naga();
        gelombangMonster[2] = new Slime();
        gelombangMonster[3] = new Zombie();

        System.out.println("=====================");
        System.out.println("ARENA RPG: GELOMBANG MONSTER");
        System.out.println("=====================\n");

        System.out.println("AWAS! Sekelompok monster menghadang Anda!");

        boolean isBermain = true;

        while (isBermain) {

            System.out.println("\n--- STATUS MONSTER ---");

            for (int i = 0; i < gelombangMonster.length; i++) {
                System.out.println((i + 1) + ". "
                        + gelombangMonster[i].namaMusuh
                        + " (HP: "
                        + gelombangMonster[i].healthPoint + ")");
            }

            System.out.println("5. Kabur dari pertarungan");

            System.out.print(
                "\nPilih target monster yang ingin diserang (1/2/3/4) atau 5 untuk kabur: ");

            int pilihanTarget = input.nextInt();

            // kabur
            if (pilihanTarget == 5) {
                System.out.println("Anda lari terbirit-birit dari arena...");
                isBermain = false;
                continue;
            }

            // validasi input
            if (pilihanTarget < 1 || pilihanTarget > 4) {
                System.out.println("Pilihan tidak valid!");
                continue;
            }

            int indeksMonster = pilihanTarget - 1;
            Musuh target = gelombangMonster[indeksMonster];

            // monster sudah mati
            if (target.healthPoint <= 0) {
                System.out.println("Monster tersebut sudah kalah!");
                continue;
            }

            System.out.print("Masukkan kekuatan serangan Anda (10 - 100): ");
            int power = input.nextInt();

            System.out.println("\n>>> HASIL SERANGAN ANDA <<<");
            target.terimaDamage(power);

            // loot drop
            if (target.healthPoint == 0) {
                if (target instanceof BisaLoot) {
                    BisaLoot lootMonster = (BisaLoot) target;
                    lootMonster.jatuhkanItem();
                }
            }

            // cek apakah semua monster mati
            boolean semuaMati = true;

            for (Musuh monster : gelombangMonster) {
                if (monster.healthPoint > 0) {
                    semuaMati = false;
                    break;
                }
            }

            // kondisi menang
            if (semuaMati) {
                System.out.println(
                    "\nSELAMAT!! Anda telah menyapu bersih gelombang monster ini!"
                );
                isBermain = false;
                continue;
            }

            System.out.println("\n<<< GILIRAN MONSTER MEMBALAS >>>");

            for (Musuh monsterAktif : gelombangMonster) {

                if (monsterAktif.healthPoint > 0) {

                    monsterAktif.suaraKhas();

                    if (monsterAktif instanceof BisaTerbang) {

                        System.out.println(
                            "[PERINGATAN SERANGAN UDARA TERDETEKSI!]"
                        );

                        BisaTerbang monsterTerbang =
                                (BisaTerbang) monsterAktif;

                        monsterTerbang.lepasLandas();
                        monsterTerbang.seranganUdara();

                    } else {
                        monsterAktif.serangPemain();
                    }
                }
            }

            System.out.println("-----------------------------------");
        }

        input.close();
        System.out.println("\nPermainan Berakhir.");
    }
}