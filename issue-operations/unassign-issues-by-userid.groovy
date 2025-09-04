import com.adaptavist.hapi.jira.exceptions.issues.IssueUpdateException
import com.adaptavist.hapi.jira.exceptions.ExceptionWithErrorCollection
    
def offboardedUserId = "712020:1556dfb7-6a6c-45c8-b122-06a7bf01704b"
def userDisplayName = "Marcus Ngeow"

def updatedKeys = []
def failed = [:] as LinkedHashMap  // key -> error message

def offboardedUser = Users.getByAccountId(offboardedUserId)
if (offboardedUser.displayName != userDisplayName) {
    println("User ${userDisplayName} does not match with Display name ${offboardedUser.displayName} of User Id ${offboardedUserId}; aborting.")
    return
}

def jql = "assignee = ${offboardedUser.accountId} AND statusCategory != Done"
def numOfIssues = Issues.count(jql)
if (numOfIssues == 0 ) {
    println("User does not have any outstanding assigneed work items; aborting.")
    return
}

println("About to process $numOfIssues issues...")
def issues = Issues.search(jql)
issues.each { issue ->
    try {

        issue.update {
            setAssignee((String) null)
            setComment("Auto-unassigned because ${offboardedUser.displayName}'s account has been offboarded/deactivated.")
            updatedKeys << issue.key
        }
    }
    catch (IssueUpdateException | ExceptionWithErrorCollection e) {
        failed[issue.key] = e.message
    }
    catch (Exception e) {
        failed[issue.key] = e.message
    }
}
// nice summaries in the Script Console output
println "Updated (${updatedKeys.size()}): ${updatedKeys.join(', ')}"
if (failed) {
  println "Failed (${failed.size()}):"
  failed.each { k, msg -> println "  - ${k}: ${msg}" }
}
// also return a JSON-like object in the console result pane
return [
  countUpdated: updatedKeys.size(),
  updatedKeys : updatedKeys,
  failed      : failed
]