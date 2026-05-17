---
name: software-engineering-phase-writer
description: Use this skill when writing or revising report phases for the Parttime Employee Management software engineering course project using teacher-reference materials as methodology only.
---

# Software Engineering Phase Writer

Use this skill to write, revise, or extend LaTeX report chapters for the Parttime Employee Management project.

## Non-negotiable boundary

The teacher-reference materials in `.claude/context/teacher-reference/` describe an unrelated hotel management system.

They are examples of **method and expected structure only**. They are not project facts.

Never copy hotel actors, entities, database rows, UI names, or scenarios into the project report unless the current project chapters independently define the same concept.

## Required workflow

1. Identify the requested phase or section.
2. Read the target LaTeX file in `chapters/`.
3. Read nearby existing chapters to understand the real project context.
4. Read the relevant teacher-reference file:
   - business model: `.claude/context/teacher-reference/01_business-model-natural-language.md`;
   - use case UML: `.claude/context/teacher-reference/02_use-case-uml.md`;
   - analysis: `.claude/context/teacher-reference/03_analysis-phase.md`;
   - design: `.claude/context/teacher-reference/04_design-phase.md`;
   - testing: `.claude/context/teacher-reference/05_testing-phase.md`.
5. Extract the teacher's structure and transform it into Parttime Employee Management content.
6. Edit only the requested chapter or section unless the user asks for broader changes.
7. Preserve the existing academic English style and LaTeX conventions.

## Adaptation rules

### Business model

Use the teacher sample to structure:

- objective and scope;
- users and their functions;
- detailed business process;
- related objects;
- object relationships.

Replace all hotel content with project-specific content.

### Use case UML

Use the teacher method:

- actors from real users;
- use cases from real functions;
- general use case diagram logic;
- detailed use case decomposition from real interfaces.

### Analysis phase

Use the teacher method:

- write scenario v.1/v.2 as concrete steps;
- extract nouns into entity candidates;
- classify nouns as entity, attribute, or rejected;
- derive quantity relationships;
- create analysis view classes from interfaces;
- assign analysis methods based on input/output entity rules;
- write analysis sequence flow.

### Design phase

Use the teacher method:

- add IDs to entity classes without inheritance;
- add data types;
- convert associations into aggregation/composition where appropriate;
- add object attributes;
- map design entity classes to database tables;
- add primary keys and foreign keys;
- define MVC-style view, DAO/control, and entity classes;
- write sequence scenario v.3 with event handlers, constructors, entity packing, and DAO calls.

### Testing phase

Use the teacher method:

- write a black-box test plan table with No., Module, and Test case;
- include standard and exceptional cases in the plan;
- for the detailed standard test case, include:
  - database before testing;
  - testing scenario and expected results;
  - database after testing.

For the current project's standard test case, use real module data for registering next week's shifts.

## Current project concepts to prefer

Verify against current LaTeX before use, but existing report has used:

- Module: Register for next week.
- Actors: registration staff, part-time employee.
- Views: LoginFrm, HomeFrm, SearchEmployeeFrm, RegisterShiftFrm.
- Entities: User, Restaurant, Employee, Shift, RegistrationShift.
- DAOs: UserDAO, EmployeeDAO, ShiftDAO, RegistrationShiftDAO.
- Tables: tblUser, tblRestaurant, tblEmployee, tblShift, tblRegistrationShift.
- Core flow: login -> home -> search employee -> select employee -> register shifts -> save.

## Quality bar

Write like a senior engineer preparing a course report:

- clear separation between reference and project facts;
- precise terminology;
- no speculative entities or functions;
- concrete test data when testing is requested;
- no unrelated refactors;
- minimal LaTeX changes needed to satisfy the request.
