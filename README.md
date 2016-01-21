# settlers
BYU CS 340 Project

### Git Instructions:

##### 1. Clone the Repo

```bash
cd ~ # move to wherever you want the repo to be
git clone https://github.com/verdude/settlers.git
```

##### 2. Make your own branch

```bash
cd ~/settlers # go to the repository folder
git branch <your branch name> # creates a new branch
git checkout <your branch name> # switches to the new branch
```

##### 3. Commit all changes

```bash
git add . # stages all files for commit
git commit -m "Title for the commit"
```

##### 4. Push (save) your changes to the github cloud

```bash
git push || git push origin <current branch name>
# `git push` will only work if you have already pushed to a remote branch
# in the case that you are trying to publish your
# local branch to the cloud, use `git push origin <current branch name>`
```

##### 5. Commit only certain files

```bash
git status # shows files that are staged for commit in green and unstaged files in red
git add <relative path to file> # stages a file for commit
# ...
git commit -m "Commit message"
```

##### 6. Update your branch

```bash
# First make sure to commit or stash your changes
git checkout master
git pull # update your local master branch copy
git checkout <your branch>
git rebase master # you may get conflicts on this step
# On any errors, let whoever worked on those files know and figure out what code you need to keep or remove
# If your changes are not important, then just `git rebase --abort` and undo your commits and then redo the rebase
```

##### 7. Update the master branch

```bash
# first, commit the changes on your branch AND update your branch (#6)
git checkout master
git merge <your branch>
# Then if there were no errors:
git push
```

##### 8. Delete your branch locally

```bash
# Do this if you have no important changes and are having annoying merge conflicts
git checkout master
git branch -D <your branch>
```

##### 9. Delete your branch's remote copy

```bash
# Do this when you want to clean your branch and there is nothing important on it
git push origin :<your branch>
```
