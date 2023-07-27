### Script by @jojofr.

def modUpdate():
    with open('mod.json', 'r+', encoding="utf8") as modFile:
        modLines = modFile.readlines()

        modFile.seek(0)
        modFile.truncate() #Remove all data from the files
        for num, line in enumerate(modLines): 
            if line.strip().startswith("\"version\":"):
                versionLine = modLines[num].split()
                # Yes it's a mess. Yes it's unreadble. Yes I don't care.
                modLines[num] = "    " + versionLine[0] + " " + versionLine[1].split('dev.')[0] + 'dev.' + str(int(versionLine[1].split('dev.')[1].strip(',').strip('"')) + 1) + "\",\n"    
            modFile.write(modLines[num]) #Rewrite the updated value
        modFile.close()

def gradleUpdate():
    with open('build.gradle', 'r+', encoding="utf8") as gradleFile:
        gradleLines = gradleFile.readlines()

        gradleFile.seek(0)
        gradleFile.truncate() #Remove all data from the files
        for num, line in enumerate(gradleLines): 
            if line.strip().startswith("version"):
                versionLine = gradleLines[num].split()
                # Yes it's a mess. Yes it's unreadble. Yes I don't care.
                gradleLines[num] = versionLine[0] + " " + versionLine[1].split('dev.')[0] + 'dev.' + str(int(versionLine[1].split('dev.')[1].strip("'")) + 1) + "'\n"    
            gradleFile.write(gradleLines[num]) #Rewrite the updated value
        gradleFile.close()
    
modUpdate()
# Works but not sure if I want to use it.
# gradleUpdate()