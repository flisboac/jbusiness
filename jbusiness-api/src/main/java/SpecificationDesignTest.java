

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class SpecificationDesignTest {

	static interface Entity {		
	}
	
	static interface Specification<T extends Entity> {
		public boolean isSatisfiableBy(T entity);
	}
	
	static interface Validator<T extends Entity> {
		public boolean isValid(T entity, Class<?>... aspects);
	}
	
	static enum PersonType {
		STUDENT,
		PROFESSOR;
	}
	
	static class Person implements Entity {
		public final String registryNumber;
		public final PersonType type;
		public String name;
		public Date registryDate = new Date();
		public Person(PersonType type, String registryNumber) {
			this.type = type;
			this.registryNumber = registryNumber;
		}
		@Override
		public int hashCode() {
			return Objects.hashCode(this.registryNumber);
		}
		@Override
		public boolean equals(Object other) {
			return other != null
					&& this.getClass().isAssignableFrom(other.getClass())
					&& Objects.equals(this.registryNumber, ((Person)other).registryNumber);
		}
	}
	
	static class Student extends Person {
		public List<StudentEnrollment> classEnrollments = new ArrayList<>();
		public Student(String registryNumber) {
			super(PersonType.STUDENT, registryNumber);
		}
	}
	
	static class Professor extends Person {
		public List<SubjectClass> subjectClasses = new ArrayList<>();;
		public double salary;
		public Professor(String registryNumber) {
			super(PersonType.PROFESSOR, registryNumber);
		}
	}
	
	static class Subject implements Entity {
		public final String code;
		public List<SubjectClass> subjectClasses = new ArrayList<>();
		public Subject(String code) {
			this.code = code;
		}
		@Override
		public int hashCode() {
			return Objects.hashCode(this.code);
		}
		@Override
		public boolean equals(Object other) {
			return other != null
					&& this.getClass().isAssignableFrom(other.getClass())
					&& Objects.equals(this.code, ((Subject)other).code);
		}
	}
	
	static class StudentEnrollment implements Entity {
		public final SubjectClass subjectClass;
		public final Student student;
		public Double grade;
		public StudentEnrollment(SubjectClass subjectClass, Student student) {
			this.subjectClass = subjectClass;
			this.student = student;
		}
		@Override
		public int hashCode() {
			return Objects.hash(subjectClass, student);
		}
		@Override
		public boolean equals(Object other) {
			return other != null
					&& this.getClass().isAssignableFrom(other.getClass())
					&& Objects.equals(this.subjectClass, ((StudentEnrollment)other).subjectClass)
					&& Objects.equals(this.student, ((StudentEnrollment)other).student);
		}
	}
	
	static class SemesterId {
		public final int year;
		public final int semester;
		public final int value;
		public SemesterId(int year, int semester) {
			this.year = year;
			this.semester = semester;
			this.value = this.year * 10 + this.semester;
		}
		public SemesterId(int year) {
			this.year = year;
			this.semester = 0;
			this.value = this.year * 10 + this.semester;
		}
		@Override
		public int hashCode() {
			return Objects.hashCode(this.value);
		}
		@Override
		public boolean equals(Object other) {
			return other != null
					&& this.getClass().isAssignableFrom(other.getClass())
					&& Objects.equals(this.value, ((SemesterId)other).value);
		}
	}
	
	static class SubjectClass implements Entity {
		public final Subject subject;
		public final SemesterId semester;
		public Professor professor;
		public List<StudentEnrollment> enrolledStudents = new ArrayList<>();
		public SubjectClass(Subject subject, SemesterId semesterId) {
			this.subject = subject;
			this.semester = semesterId;
		}
		@Override
		public int hashCode() {
			return Objects.hash(subject, semester);
		}
		@Override
		public boolean equals(Object other) {
			return other != null
					&& this.getClass().isAssignableFrom(other.getClass())
					&& Objects.equals(this.subject, ((SubjectClass)other).subject)
					&& Objects.equals(this.semester, ((SubjectClass)other).semester);
		}
	}
	
	static class PersonValidator implements Validator<Person> {
		public boolean isValid(Person subject, Class<?>... aspects) {
			return subject != null && subject.name != null && !subject.name.isEmpty();
		}
	}
	
	static class StudentValidator implements Validator<Student> {
		public PersonValidator personValidator = new PersonValidator(); 
		public boolean isValid(Student student, Class<?>... aspects) {
			return personValidator.isValid(student, aspects) && student.type == PersonType.STUDENT;
		}
	}
	
	static class CompositeSpecification<T extends Entity> implements Specification<T> {
		private final List<Specification<? super T>> specs = new ArrayList<>();
		@SafeVarargs
		public CompositeSpecification(Specification<? super T>... specs) {
			this.specs.addAll(Arrays.asList(specs));
		}
		public Specification<T> and(Specification<? super T> op) {
			specs.add(op);
			return this;
		}
		@Override
		public boolean isSatisfiableBy(T entity) {
			boolean result = true;
			for (Specification<? super T> specification : specs) {
				result = result && specification.isSatisfiableBy(entity);
			}
			return result;
		}
	}

	static class PersonHasOneYearOfRegistrationSpecification implements Specification<Person> {
		@Override
		public boolean isSatisfiableBy(Person student) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(student.registryDate);
			cal.add(Calendar.YEAR, 1);
			return cal.getTime().compareTo(new Date()) <= 0;
		}		
	}
	
	static class StudentIsEnrolledInAnySubjectSpecification implements Specification<Student> {
		@Override
		public boolean isSatisfiableBy(Student student) {
			return !student.classEnrollments.isEmpty();
		}		
	}

	
	static class StudentIsEnrolledInSubjectSpecification implements Specification<Student> {
		public final Subject subject;
		public StudentIsEnrolledInSubjectSpecification(Subject subject) {
			this.subject = subject;
		}
		@Override
		public boolean isSatisfiableBy(Student student) {
			for (StudentEnrollment enrollment : student.classEnrollments) {
				if (enrollment.subjectClass.subject.equals(subject)) {
					return true;
				}
			}
			return false;
		}
	}
	
	static class SubjectHasClassesSpecification implements Specification<Subject> {
		@Override
		public boolean isSatisfiableBy(Subject subject) {
			return !subject.subjectClasses.isEmpty();
		}
	}
	
	static class DatabaseStub {
		public final Random random = new Random();
		public final Set<Person> persons = new LinkedHashSet<>();
		public final Set<Subject> subjects = new LinkedHashSet<>();
		public final Set<SubjectClass> subjectClasses = new LinkedHashSet<>();
		public final Set<StudentEnrollment> studentEnrollments = new LinkedHashSet<>();
		
		public Student randomStudent() {
			Student student;
			do {
				int number = random.nextInt(4);
				student = new Student(String.format("st-%04d%04d",
					Calendar.getInstance().get(Calendar.YEAR),
					number));
				student.name = String.format("Student %04d", number);
			} while (!persons.contains(student));
			persons.add(student);
			return student;
		}

		public Professor randomProfessor() {
			Professor professor;
			do {
				int number = random.nextInt(4);
				professor = new Professor(String.format("pr-%04d%04d",
					Calendar.getInstance().get(Calendar.YEAR),
					number));
				professor.name = String.format("Professor %04d", number);
			} while (persons.contains(professor));
			persons.add(professor);
			return professor;
		}

		public Subject randomSubject() {
			Subject subject;
			do {
				int number = random.nextInt(4);
				subject = new Subject(String.format("sub-%04d", number));
			} while (subjects.contains(subject));
			subjects.add(subject);
			return subject;
		}
		
		public SubjectClass save(SubjectClass subjectClass) {
			subjectClasses.remove(subjectClass);
			subjectClasses.add(subjectClass);
			return subjectClass;
		}
	}
	
	public static void main(String[] args) {
		// How to implement this?
		Specification<Student> personSpec = new CompositeSpecification<Student>(
					new StudentIsEnrolledInAnySubjectSpecification())
				.and(new PersonHasOneYearOfRegistrationSpecification());
				// .and(new SubjectHasClassesSpecification()); -- OOPS Cannot do!

		Student student = new Student("MATRICULA");
		StudentIsEnrolledInAnySubjectSpecification spec = new StudentIsEnrolledInAnySubjectSpecification();

		if (personSpec.isSatisfiableBy(student)) {
			// ...
		}
		if (spec.isSatisfiableBy(student)) {
			
		}
	}
}