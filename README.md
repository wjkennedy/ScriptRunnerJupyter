# ScriptRunner Xerox

## Introduction
This is a Jupyter Notebook that generates Groovy scripts based on a provided example and a projects dataframe. The projects dataframe is provided by an uploaded CSV file, and the Groovy script example is provided by an uploaded file. The Groovy scripts are generated for selected projects from the dataframe and written to a directory named generated_YYmmdd (with YYmmdd being the current date).

## Usage
Open the Jupyter Notebook file.

run the code to import the necessary libraries and initialize the ipywidgets.

use the Upload widget to upload the projects CSV file.

use the Upload widget to upload the Groovy script example file.

select the projects for which you want to generate the Groovy scripts using the checkboxes.

click the Generate Groovy Scripts button to generate the Groovy scripts and write them to the generated_YYmmdd directory. The names of the generated Groovy scripts will be printed in the output.

## Note

The code assumes that the uploaded CSV file has a Project Name column with the names of the projects and that the uploaded Groovy script example has the string {project} to be replaced with the names of the projects.

## Example projects.csv

Project Name
GREAYS
TUPIL
ESOR

## Example example.groovy

	import com.atlassian.jira.component.ComponentAccessor
	import com.atlassian.jira.issue.Issue
	import com.atlassian.jira.issue.IssueManager
	import com.atlassian.jira.issue.fields.FieldManager
	import com.atlassian.jira.project.Project
	import com.atlassian.jira.project.ProjectManager

	def projectManager = ComponentAccessor.getProjectManager()
	def issueManager = ComponentAccessor.getIssueManager()
	def fieldManager = ComponentAccessor.getFieldManager()

	def projectKey = "{project}"

	def project = projectManager.getProjectByCurrentKey(projectKey)

	// Get Project Info
	println("Project: " + project.name + " Key: " + project.key)
	println("Description: " + project.description)

	// Get Components
	println("\nComponents:")
	project.components.each { component ->
	    println(" - " + component.name)
	}

	// Get Workflows
	println("\nWorkflows:")
	def workflowManager = ComponentAccessor.getWorkflowManager()
	workflowManager.getWorkflowsForProject(project).each { workflow ->
	    println(" - " + workflow.name)
	}

	// Get Issues
	println("\nIssues:")
	def issues = issueManager.getIssuesFromJqlSearch("project = " + projectKey, 1000)
	issues.each { issue ->
	    println(" - " + issue.key + ": " + issue.summary)
	}

	// Get Custom Fields
	println("\nCustom Fields:")
	fieldManager.getAllAvailableNavigableFields().each { field ->
	    println(" - " + field.name)
	}

