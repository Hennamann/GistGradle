package com.henrikstabell.gistgradle.tasks;

import com.henrikstabell.gistgradle.GistGradlePlugin;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.service.GistService;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;
import java.util.Collections;

/**
 * @see <a href="https://opensource.org/licenses/EUPL-1.1">License</a> for what you can and can't do with the code.
 * Created by Hennamann(Ole Henrik Stabell) on 19/03/2018.
 */
public class EditGistTask extends DefaultTask {

    private String gitUser;
    private String gitPass;

    private String gistID;
    private String gistContent;
    private String gistFileName;

    @Input
    public String getGitUser() { return gitUser; }
    public void setGitUser(String gitUser) { this.gitUser = gitUser; }

    @Input
    public String getGitPass() { return gitPass; }
    public void setGitPass(String gitPass) { this.gitPass = gitPass; }

    @Input
    public String getGistID() { return gistID; }
    public void setGistID(String gistID) { this.gistID = gistID; }

    @Input
    public String getGistContent() { return gistContent; }
    public void setGistContent(String gistContent) { this.gistContent = gistContent; }

    @Input
    public String getGistFileName() { return gistFileName; }
    public void setGistFileName(String gistFileName) { this.gistFileName = gistFileName; }

    @TaskAction
    void editGist() throws IOException {
        GistService gistService = new GistService();
        gistService.getClient().setCredentials(getGitUser(), getGitPass());
        Gist gist = gistService.getGist(getGistID());

        GistFile gistFile = new GistFile();
        gistFile.setContent(getGistContent());
        gistFile.setFilename(getGistFileName());

        gist.setFiles(Collections.singletonMap(getGistFileName(), gistFile));
        gist.setId(getGistID());
        gistService.updateGist(gist);

        GistGradlePlugin.slf4jLogger.info("Susccesfully edited gist with id " + getGistID() + " at URL: " + gist.getHtmlUrl());
    }

    @OutputFile
    public String getFinalGistID() { return gistID; }

}