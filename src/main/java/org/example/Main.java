package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Main {

    private final VisitService visitService = new VisitService();

    public static void main(String[] args) {
        //aby nie korzystac z kontekstu statycznego
        Main main = new Main();
        main.run();
    }

    private void run() {

        //Dodaj doktorow do bazy danych jesli to pierwsze uruchomienie aplikacji
        //visitService.fillDatabase();

            //pokazuje menu
            showMenu();

            //blok try with resources aby reader zamykal sie automatycznie
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                int option = Integer.parseInt(reader.readLine());

                switch (option) {
                    case 1 -> {
                        //Dodaje wizyte do bazy danych C
                        showEnableDoctorsType();
                        insertTypeOfDoctor(reader);
                    }
                    case 2 -> {
                        //Usuwa wizyte z bazy danych D
                        deleteVisit(reader);
                    }
                    case 3 -> {
                        //Edytuje wizyte w bazie danych U
                        editVisit(reader);
                    }
                    case 4 -> {
                        //Pokazuje wizyty z bazy danych R
                        showVisitById(reader);
                    }
                    default -> {
                        System.out.println("Nie ma takiej opcji");
                    }
                }

            } catch (Exception e) {
                //ignoruje
            }

    }

    private void showVisitById(BufferedReader reader) throws IOException {
        System.out.println("Podaj ID wizyty");
        Long id = Long.parseLong(reader.readLine());
        System.out.println(visitService.getVisitById(id));
    }

    private void editVisit(BufferedReader reader) throws IOException {
        System.out.println("Podaj ID swojej wizyty");
        Long id = Long.parseLong(reader.readLine());
        visitService.updateVisit(id, reader);
        System.out.println("Wizyta o ID: " + id + " zostala zaktualizowana");
    }

    private void deleteVisit(BufferedReader reader) throws IOException {
        System.out.println("Podaj ID wizyty do usuniecia");
        Long id = Long.parseLong(reader.readLine());
        visitService.deleteVisit(id);
        System.out.println("Wizyta o ID: " + id + " zostala usunieta");
    }

    private void showResultOfVisit(Long id) {
        Visit visit = visitService.getVisitById(id);
        System.out.println("Wizyta umowiona na dzien: " + visit.getVisitDate());
        System.out.println("ID wizyty: " + visit.getId());
    }

    private void insertTypeOfDoctor(BufferedReader reader) throws IOException {
        int option = Integer.parseInt(reader.readLine());

        switch (option) {
            case 1 -> {
                //Dodaje wizyte do bazy danych
                Visit visit = new Visit();
                visit.setDoctor(visitService.getDoctorById(1L));
                visit.setDocktorType(DocktorType.DERMATOLOGIST);
                visit.setDescription("Wizyta u dermatologa");
                visit.setVisitDate(LocalDateTime.now());
                visit.setCreatedDate(LocalDateTime.now().plusDays(7));
                visitService.saveVisit(visit);
                showResultOfVisit(visit.getId());
            }
            case 2 -> {
                //Dodaje wizyte do bazy danych
                Visit visit1 = new Visit();
                visit1.setDoctor(visitService.getDoctorById(2L));
                visit1.setDocktorType(DocktorType.NEUROLOGIST);
                visit1.setDescription("Wizyta u ginekologa");
                visit1.setVisitDate(LocalDateTime.now());
                visit1.setCreatedDate(LocalDateTime.now().plusDays(7));
                visitService.saveVisit(visit1);
                showResultOfVisit(visit1.getId());
            }
            case 3 -> {
                //Dodaje wizyte do bazy danych
                Visit visit2 = new Visit();
                visit2.setDoctor(visitService.getDoctorById(3L));
                visit2.setDocktorType(DocktorType.UROLOGIST);
                visit2.setDescription("Wizyta u kardiologa");
                visit2.setVisitDate(LocalDateTime.now());
                visit2.setCreatedDate(LocalDateTime.now().plusDays(7));
                visitService.saveVisit(visit2);
                showResultOfVisit(visit2.getId());
            }
            default -> System.out.println("Nie ma takiej opcji");
        }
    }

    private void showEnableDoctorsType() {
        System.out.println("==============TYP================");
        System.out.println("1 - DERMATOLOGIST");
        System.out.println("2 - NEUROLOGIST");
        System.out.println("3 - UROLOGIST");
    }

    private void showMenu() {
        System.out.println("==============MENU================");
        System.out.println("1 - Dodaj wizyte");
        System.out.println("2 - Usun wizyte");
        System.out.println("3 - Edytuj wizyte");
        System.out.println("4 - Pokaz wizyte");
    }


}