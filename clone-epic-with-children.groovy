def originalEpicKey = 'EDI-38'
def targetProjectKey = 'EDP'

// Fetch the original Epic
def originalEpic = Issues.getByKey(originalEpicKey)
if (!originalEpic) {
    logger.error("Epic '${originalEpicKey}' not found.")
    return
}

// Clone the Epic into the target project
def clonedEpic = Issues.create(targetProjectKey, originalEpic.issueType.name) {
    setSummary("${originalEpic.summary}")
    setDescription(originalEpic.description)
}

// Sanity check
if (!clonedEpic) {
    logger.error("Failed to clone Epic.")
    return
}

// Fetch child issues linked via Epic Link
def childIssues = Issues.search("issuetype in (Story, Task, Bug) AND 'Parent' = ${originalEpicKey}")

childIssues.each { issue ->
    // Clone each child into the target project and re-link to the new Epic
    Issues.create(targetProjectKey, issue.issueType.name) {
        setSummary("${issue.summary}")
        setDescription(issue.description)
        setParent(clonedEpic.key)
    }
}

return "✅ Cloned Epic ${originalEpicKey} to ${clonedEpic.key}, with '${childIssues.size()}' child issues cloned into ${targetProjectKey}."