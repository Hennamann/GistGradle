# GistGradle
Gradle plugin for creating and editing GitHub gists using your buildscripts

## Installation
The plugin is hosted on the official Gradle plugins repo and can be added to your build script like any plugin:

```groovy
plugins {
  id "com.henrikstabell.gistgradle" version "1.0-SNAPSHOT"
}
```
https://plugins.gradle.org/plugin/com.henrikstabell.gistgradle

## Usage
The plugin has two tasks, both of which are optional:

### createGist
Creates a new Gist on your GitHub account:
```groovy
createGist {
	gitUser = "github username"
	gitPass = "github password"
	gistContent = "Contents of the file you are creating"
	gistFileName = "filename.txt" 
	gistStatus = true
}
```

**gitUser** expects a GitHub username. **Mandatory**

**gitPass** expects a GitHub password, if you have 2FA enabled on your GitHub account this will not work, support for OAuth might be added in the future (PRs are welcome!). **Mandatory**

**gistContent** expects a text string with your file contents, this can be any format. (JSON, TXT, XML, HTML etc.). **Mandatory**

**gistFileName** expects a filename with a file extension. **Mandatory**

**gistDescription** expects a text string with a short description for your gist, if this is ignored a default description will be added to the gist for you. *Optional*

**gistStatus** Set this to true if you want the gist to be public (default: false) *Optional*

With the exception of `gistDescription` and `gistStatus` all parameters are mandatory, the plugin will fail with a NullPointerException if the mandatory parameters are empty.

to run the task use the following command:
```bash
gradle createGist
```

### editGist
Edits an existing gist on your GitHub account.
```groovy
editGist {
	gitUser = "github username"
	gitPass = "github password"
    gistID = "12345"
	gistContent = "Contents of the file you are creating"
	gistFileName = "filename.txt" 
}
```

**gitUser** expects a GitHub username. **Mandatory**

**gitPass** expects a GitHub password, if you have 2FA enabled on your GitHub account this will not work, support for OAuth might be added in the future (PRs are welcome!). **Mandatory**

**gistID** expects the ID of the gist you want to edit (ex. ec632e84cd5e0941259c7746d04ddb03) **Mandatory**

**gistContent** expects a text string with your file contents, this can be any format. (JSON, TXT, XML, HTML etc.). **Mandatory**

**gistFileName** expects a filename with a file extension. **Mandatory**

All parameters are mandatory, the plugin will fail with a NullPointerException if any of the parameters are empty.

to run the task use the following command:
```bash
gradle editGist
```

## Limitations
This plugin was made for a specific personal use case, but published because others might have a use for it as well. As such it does what it needs to and not much else. This causes some limitations:

* Both when editing and creating gists, only a single file can be added to the gist. This is not a limitation with the API but the plugin, if you really need this added feel free to make a PR and i'll happily merge it.
* When editing a gist the content is completley replaced instead of merging the changes.

I have no plans to remove these limitations but feel free to submit a pull request and i'll happily merge it.

## Dependencies
This plugin uses the GitHub Java API by Eclipse to create and edit gists: https://github.com/eclipse/egit-github/tree/master/org.eclipse.egit.github.core 
