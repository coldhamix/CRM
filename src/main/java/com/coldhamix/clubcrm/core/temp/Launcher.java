package com.coldhamix.clubcrm.core.temp;

import com.coldhamix.clubcrm.core.dao.StudentManager;
import com.coldhamix.clubcrm.core.dao.VisitManager;
import com.coldhamix.clubcrm.core.domain.Student;
import com.coldhamix.clubcrm.core.domain.Visit;
import com.coldhamix.clubcrm.core.util.HibernateUtil;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Copyright 2016 (c) ClubCRM.
 * Author: Vadim A. Khamzin.
 * Created on: 23-01-2016
 */
public class Launcher {
    // TODO: This class is only for testing purposes

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        menu:
        for (; ; ) {
            System.out.println("Choose one of the next entity types: ");
            System.out.println("student");
            System.out.println("visit");
            System.out.println("mapping_test");
            System.out.println("exit");
            String response = scanner.nextLine();

            try (
                    StudentManager studentManager = new StudentManager();
                    VisitManager visitManager = new VisitManager()
            ) {

                switch (response) {
                    case "student":
                        System.out.println("What method would you like to execute?");
                        System.out.println("- create");
                        System.out.println("- get");
                        System.out.println("- update");
                        System.out.println("- delete");
                        System.out.println("- list");
                        System.out.println("- add_visit");
                        System.out.println("- remove_visit");

                        response = scanner.nextLine();
                        switch (response) {
                            case "create":
                                System.out.print("Name: ");
                                String name = scanner.nextLine();

                                Student student = new Student();
                                student.setName(name);
                                student.setBirthday(new Date());
                                studentManager.create(student);
                                continue menu;

                            case "get":
                                System.out.println("Enter id: ");
                                Long id = scanner.nextLong();
                                scanner.nextLine(); // skip NL char

                                student = studentManager.findById(id);
                                System.out.println("#" + student.getId() + ". " + student.getName() + ", born-on: " + student.getBirthday().toString());
                                continue menu;

                            case "update":
                                System.out.println("Enter id: ");
                                id = scanner.nextLong();
                                student = studentManager.findById(id);
                                scanner.nextLine(); // skip NL char

                                System.out.print("Name: ");
                                name = scanner.nextLine();

                                student.setName(name);
                                student.setBirthday(new Date());
                                studentManager.update(student);
                                continue menu;

                            case "delete":
                                System.out.println("Enter id: ");
                                id = scanner.nextLong();
                                scanner.nextLine(); // skip NL char
                                studentManager.delete(id);
                                continue menu;

                            case "list":
                                List<Student> allStudents = studentManager.list();
                                for (Student s : allStudents) {
                                    System.out.println("#" + s.getId() + ". " + s.getName() + ", born-on: " + s.getBirthday().toString());
                                }
                                continue menu;

                            case "add_visit":
                                System.out.println("Enter student id: ");
                                id = scanner.nextLong();
                                scanner.nextLine(); // skip NL char

                                System.out.println("Enter visit id: ");
                                Long visitId = scanner.nextLong();
                                scanner.nextLine(); // skip NL char

                                student = studentManager.findById(id);
                                Visit visit = visitManager.findById(visitId);
                                student.addVisit(visit);

                                continue menu;

                            case "remove_visit":
                                System.out.println("Enter student id: ");
                                id = scanner.nextLong();
                                scanner.nextLine(); // skip NL char

                                System.out.println("Enter visit id: ");
                                visitId = scanner.nextLong();
                                scanner.nextLine(); // skip NL char

                                student = studentManager.findById(id);
                                visit = visitManager.findById(visitId);
                                student.removeVisit(visit);

                                continue menu;

                            default:
                                System.out.println("Unsupported operation");
                        }
                        continue menu;

                    case "visit":
                        System.out.println("What method would you like to execute?");
                        System.out.println("- create");
                        System.out.println("- get");
                        System.out.println("- update");
                        System.out.println("- delete");
                        System.out.println("- list");

                        response = scanner.nextLine();
                        switch (response) {
                            case "create":
                                System.out.println("Type: 0 - Normal, 1 - Additional, 2 - Individual");
                                int type = scanner.nextInt();
                                scanner.nextLine(); // skip NL char

                                Visit visit = new Visit();
                                visit.setTypeOfVisit(type);
                                visit.setVisitDate(new Date());
                                visitManager.create(visit);
                                continue menu;

                            case "get":
                                System.out.println("Enter id: ");
                                Long id = scanner.nextLong();
                                scanner.nextLine(); // skip NL char

                                visit = visitManager.findById(id);
                                System.out.println("#" + visit.getId() + ". " + visit.getTypeOfVisit() + ", date: " + visit.getVisitDate().toString());
                                continue menu;

                            case "update":
                                System.out.println("Enter id: ");
                                id = scanner.nextLong();
                                visit = visitManager.findById(id);
                                scanner.nextLine(); // skip NL char

                                System.out.println("Type: 0 - Normal, 1 - Additional, 2 - Individual");
                                type = scanner.nextInt();
                                scanner.nextLine(); // skip NL char

                                visit.setTypeOfVisit(type);
                                visit.setVisitDate(new Date());
                                visitManager.update(visit);
                                continue menu;

                            case "delete":
                                System.out.println("Enter id: ");
                                id = scanner.nextLong();
                                scanner.nextLine(); // skip NL char
                                visitManager.delete(id);
                                continue menu;

                            case "list":
                                List<Visit> allVisits = visitManager.list();
                                for (Visit v : allVisits) {
                                    System.out.println("#" + v.getId() + ". " + v.getTypeOfVisit() + ", date: " + v.getVisitDate().toString());
                                }
                                continue menu;
                            default:
                                System.out.println("Unsupported operation");
                        }
                        continue menu;

                    case "mapping_test":
                        System.out.println("Enter id: ");
                        Long id = scanner.nextLong();
                        Student student = studentManager.findById(id);
                        scanner.nextLine(); // skip NL char

                        System.out.println("Student " + student.getName() + " has next visits: ");
                        for (Visit v : student.getVisits()) {
                            System.out.println("On " + v.getVisitDate() + " visited a lesson type " + v.getTypeOfVisit() + ". Id: " + v.getId());
                        }
                        continue menu;

                    default:
                        System.out.println("Unsupported entity");
                        System.out.println("Good bye");
                        break menu;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        HibernateUtil.getSessionFactory().close();
    }

}
