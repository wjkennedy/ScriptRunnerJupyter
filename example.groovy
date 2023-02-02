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

