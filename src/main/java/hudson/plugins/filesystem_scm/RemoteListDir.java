package hudson.plugins.filesystem_scm;

import java.util.*;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import hudson.FilePath.FileCallable;
import hudson.remoting.VirtualChannel;
import org.jenkinsci.remoting.RoleChecker;

public class RemoteListDir implements FileCallable< Set<String> > { 
	
	private static final long serialVersionUID = 1452212500874165127L;

	public RemoteListDir() {
	}
	
	public Set<String> invoke(File workspace, VirtualChannel channel) throws IOException {
		Collection<File> list = FileUtils.listFiles(workspace, null, true);
		Set<String> set = new HashSet<String>();
		for(File file : list) {
			String relativePath = FolderDiff.getRelativeName(file.getAbsolutePath(), workspace.getAbsolutePath());
			set.add(relativePath);
		}
		return set;
	}

   @Override
   public void checkRoles(RoleChecker checker) throws SecurityException {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
}
