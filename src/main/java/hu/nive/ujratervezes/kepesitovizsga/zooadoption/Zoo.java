package hu.nive.ujratervezes.kepesitovizsga.zooadoption;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Zoo {

    private Map<Person, Adoption> adoptions = new HashMap<>();

    public Map<Person, Adoption> getAdoptions() {
        return new HashMap<>(adoptions);
    }

    public void loadAdoptiveParentsFromDatabase(DataSource dataSource) {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from zooadoptions;")) {
            while (rs.next()) {
                String name = rs.getString(2);
                String email = rs.getString(3);
                String animal = rs.getString(4);
                LocalDate adoptionDate = rs.getDate(5).toLocalDate();
                adoptions.put(new Person(name, email), new Adoption(animal, adoptionDate));
            }
        } catch (SQLException sqle) {
            throw new IllegalStateException("Can not get data", sqle);
        }
    }

    public List<String> getTextsOfLetters(String filename) {
        List<String> texts = new ArrayList<>();
        String templateText = readFromFile(filename);
        for (Person p : adoptions.keySet()) {
            Map<String, Object> data = getData(p, adoptions.get(p));
            texts.add(getTextOfLetter(templateText, data));
        }
        return texts;
    }

    private String readFromFile(String filename) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Zoo.class.getResourceAsStream("/" + filename)))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file.", ioe);
        }
        return sb.toString().trim();
    }

    private Map<String, Object> getData(Person person, Adoption adoption) {
        Map<String, Object> data = new HashMap<>();

        data.put("{orokbefogado_szulo}", person.getName());
        data.put("{datum}", LocalDate.of(2021, 10, 4));
        data.put("{idopont}", LocalTime.of(17, 0));
        data.put("{orokbefogadott_allat}", adoption.getAnimal());

        return data;
    }

    private String getTextOfLetter(String templateText, Map<String, Object> data) {
        for (String s : data.keySet()) {
            templateText = templateText.replace(s, data.get(s).toString());
        }
        return templateText;
    }

    public Adoption getFirstAdoption() {
        LocalDate first = LocalDate.now().plusDays(1);
        Adoption adoption = null;
        for (Adoption a : adoptions.values()) {
            if (a.getAdoptionDate().isBefore(first)) {
                first = a.getAdoptionDate();
                adoption = a;
            }
        }
        return adoption;
    }

    public List<String> getAnimalNamesReverseOrdered() {
        List<String> animalNames = new ArrayList<>();
        for (Adoption a : adoptions.values()) {
            animalNames.add(a.getAnimal());
        }
        Collections.sort(animalNames);
        Collections.reverse(animalNames);

        return animalNames;
    }

    public Person getExactPerson(String email) {
        for (Person p : adoptions.keySet()) {
            if (p.getEmail().equals(email)) {
                return p;
            }
        }
        throw new IllegalArgumentException("No such person among the adoptive parents!");
    }
}
