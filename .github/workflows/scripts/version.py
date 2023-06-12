### Script by 🌟 Jojo#6430

with open('./src/assets/mod.json', 'r+', encoding="utf8") as fileMod:
    lineMod = fileMod.readlines()
    version = lineMod[4].split() #Get the line with version value
    version = version[1].replace('"', '').replace(',', '') #Get the build version number and remove some values

    newVersion = str(int(version) + 1) #Increment the build version by 1
    
    lineMod[4] = f'    "version": "{newVersion}",\n' #Change the version in the list
    fileMod.seek(0)
    fileMod.truncate() #Remove all data
    for lineNum, line in enumerate(lineMod):
        fileMod.write(lineMod[lineNum]) #Write one by one the value again
    fileMod.close()