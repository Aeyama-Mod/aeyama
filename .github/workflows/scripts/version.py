### Script by @jojofr.

with open('mod.json', 'r+', encoding="utf8") as f:
    lines = f.readlines()

    f.seek(0)
    f.truncate() #Remove all data from the files
    for num, line in enumerate(lines): 
        if line.strip().startswith("\"version\":"):
            versionLine = lines[num].split()
            # Yes it's a mess. Yes it's unreadble. Yes I don't care.
            lines[num] = "    " + versionLine[0] + " " + versionLine[1].split('dev.')[0] + 'dev.' + str(int(versionLine[1].split('dev.')[1].strip(',').strip('"')) + 1) + "\",\n"    
        f.write(lines[num]) #Rewrite the updated value
    f.close()