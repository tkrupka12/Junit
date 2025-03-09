package patientintake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClinicCalendarTest {
    private static ClinicCalendar calendar;
    @BeforeAll
    static void testSetup(){

    }
    @BeforeEach
     void init( ) {

        calendar = new ClinicCalendar(LocalDate.now());
    }
    @Test
    public void allowEntryOfAppointment() {
      //  ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        calendar.addAppointment("Miriam", "Kohen", "avery", "2/23/2025 9:00 PM");
        calendar.addAppointment("Tova", "Krupka", "avery", "2/23/2025 9:30 PM");
        List<PatientAppointment> appointments = calendar.getAppointments();
        Assertions.assertNotNull(appointments);
        assertEquals(2, appointments.size());

        PatientAppointment enteredAppt = appointments.get(0);
        assertEquals("Tova", "Miriam", enteredAppt.getPatientFirstName());
        assertEquals("Krupka", "Kohen", enteredAppt.getPatientLastName());
        assertEquals(Doctor.avery, enteredAppt.getDoctor());
        enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a"));
    }

    @Test
    public void returnTrueForHasAppointmentsIfThereAreAppointments() {
      //  ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        calendar.addAppointment("Shmuel", "Jacobs", "avery",
                "2/23/2025 9:30 AM");
        assertTrue(calendar.hasAppointments(LocalDate.of(2025, 2, 23)));
    }

    @Test
    public void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
       // ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        assertFalse(calendar.hasAppointments(LocalDate.of(2025, 2, 28)));

    }
    @Test
    void returnCurrentDaysAppointments() {
     // ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        calendar.addAppointment("Shmuel", "Jacobs", "avery",
                "today 2:00 pm");
        calendar.addAppointment("Shira", "Jacobs", "avery",
                "2/27/2025 3:00 pm");
        calendar.addAppointment("Devorah", "Jacobs", "avery",
                "3/31/2025 2:00 pm");
       assertIterableEquals(calendar.getAppointments(), calendar.getTodaysAppointments());

    }
}
