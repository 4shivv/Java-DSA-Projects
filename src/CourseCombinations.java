// Author: Shivaganesh Nagamandla
// Course: CS2
// Semester: Spring 2025

import java.util.*;

public class CourseCombinations 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);

		int numStudents = Integer.parseInt(scanner.nextLine().trim());

		// HashMap to store student-course relationships
		HashMap<String, HashSet<String>> studentCourses = new HashMap<>();

		// HashMap to store course-to-students mapping
		HashMap<String, HashSet<String>> courseToStudents = new HashMap<>();

		// Read input and build mappings
		for (int i = 0; i < numStudents; i++) 
		{
			String[] input = scanner.nextLine().split(" ");
			String student = input[0];

			HashSet<String> courses = new HashSet<>();

			for (int j = 1; j < input.length; j++) 
			{
				String course = input[j];
				courses.add(course);

				// Map course to students
				courseToStudents.putIfAbsent(course, new HashSet<>());
				courseToStudents.get(course).add(student);
			}
			studentCourses.put(student, courses);
		}

		scanner.close();

		// HashSet to store unique student pairs
		HashSet<String> studentPairs = new HashSet<>();

		// Find student pairs who share at least one course
		for (HashSet<String> students : courseToStudents.values()) 
		{
			List<String> studentList = new ArrayList<>(students);

			for (int i = 0; i < studentList.size(); i++) {
				for (int j = i + 1; j < studentList.size(); j++) {
					String studentA = studentList.get(i);
					String studentB = studentList.get(j);

					// Ensure consistent ordering of pairs
					String pair = studentA.compareTo(studentB) < 0 ? studentA + "," + studentB
							: studentB + "," + studentA;

					studentPairs.add(pair);
				}
			}
		}

		// Output the number of unique student pairs
		System.out.println(studentPairs.size());
	}
}
