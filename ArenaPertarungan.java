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

                if (gelombangMonster[i].healthPoint > 0) {
                    System.out.println((i + 1) + ". "
                            + gelombangMonster[i].namaMusuh
                            + " (HP: "
                            + gelombangMonster[i].healthPoint + ")");
                } else {
                    System.out.println((i + 1) + ". "
                            + gelombangMonster[i].namaMusuh
                            + " [TEWAS]");
                }
            }

            System.out.println("5. Kabur dari pertarungan");
            System.out.print("Pilih target monster (1-4) atau 5 untuk kabur: ");

            try {

                // input target monster
                int pilihanTarget = input.nextInt();

                if (pilihanTarget == 5) {
                    System.out.println("Anda lari dari arena...");
                    isBermain = false;
                    continue;
                }

                if (pilihanTarget < 1 || pilihanTarget > 4) {
                    System.out.println("Pilihan tidak valid!");
                    continue;
                }

                int indeksMonster = pilihanTarget - 1;

                // cek monster sudah mati
                if (gelombangMonster[indeksMonster].healthPoint <= 0) {
                    throw new TargetMatiException(
                            "Tindakan ilegal: Anda tidak bisa menyerang monster yang sudah mati!");
                }

                // input power serangan
                System.out.print("Masukkan kekuatan serangan Anda (10-100): ");
                int power = input.nextInt();

                // validasi power
                if (power < 10 || power > 100) {
                    throw new SeranganTidakValidException(
                            "Kekuatan serangan harus di antara 10 sampai 100!");
                }

                System.out.println("\n>>> HASIL SERANGAN ANDA <<<");
                gelombangMonster[indeksMonster].terimaDamage(power);

                // loot drop
                if (gelombangMonster[indeksMonster].healthPoint <= 0) {

                    System.out.println(
                            gelombangMonster[indeksMonster].namaMusuh
                                    + " berhasil dikalahkan!");

                    if (gelombangMonster[indeksMonster] instanceof BisaLoot) {

                        BisaLoot monsterLoot =
                                (BisaLoot) gelombangMonster[indeksMonster];

                        monsterLoot.jatuhkanItem();
                    }
                }

            }

            // input huruf/simbol
            catch (java.util.InputMismatchException e) {
                System.out.println(
                        "ERROR INPUT: Anda harus memasukkan ANGKA!");
                input.nextLine(); // membersihkan buffer
            }

            // menyerang monster mati
            catch (TargetMatiException e) {
                System.out.println(
                        "KESALAHAN GAME: " + e.getMessage());
            }

            // power tidak valid
            catch (SeranganTidakValidException e) {
                System.out.println(
                        "KESALAHAN GAME: " + e.getMessage());
            }

            // error lain
            catch (Exception e) {
                System.out.println(
                        "Terjadi kesalahan sistem: " + e.getMessage());
            }

            // cek semua monster mati
            boolean semuaMati = true;

            for (Musuh monster : gelombangMonster) {
                if (monster.healthPoint > 0) {
                    semuaMati = false;
                    break;
                }
            }

            if (semuaMati) {
                System.out.println(
                        "\nSELAMAT! Anda telah mengalahkan semua monster!");
                isBermain = false;
                continue;
            }

            System.out.println("\n<<< GILIRAN MONSTER MEMBALAS >>>");

            for (Musuh monsterAktif : gelombangMonster) {

                if (monsterAktif.healthPoint > 0) {

                    monsterAktif.suaraKhas();

                    if (monsterAktif instanceof BisaTerbang) {

                        System.out.println(
                                "[PERINGATAN SERANGAN UDARA TERDETEKSI!]");

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
