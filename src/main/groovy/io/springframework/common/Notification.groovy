package io.springframework.common

/**
 * A trait to append notifications to Slack
 *
 * @author Marcin Grzejszczak
 */
trait Notification {

	void slackNotification(Node rootNode, String roomName) {
		Node propertiesNode = rootNode / 'publishers'
		def slack = propertiesNode / 'jenkins.plugins.slack.SlackNotifier'
		(slack / 'room').setValue(roomName)
		(slack / 'startNotification').setValue(false)
		(slack / 'notifySuccess').setValue(false)
		(slack / 'notifyAborted').setValue(false)
		(slack / 'notifyNotBuilt').setValue(false)
		(slack / 'notifyUnstable').setValue(true)
		(slack / 'notifyFailure').setValue(true)
		(slack / 'notifyBackToNormal').setValue(true)
		(slack / 'notifyRepeatedFailure').setValue(true)
		(slack / 'includeTestSummary').setValue(true)
		(slack / 'showCommitList').setValue(true)
		(slack / 'commitInfoChoice').setValue('AUTHORS_AND_TITLES')
	}

}
