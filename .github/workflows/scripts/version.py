### Script by 🌟 Jojo#6430
src = '.'

with open(f'{src}/mod.hjson', 'r+') as fileMod:
    lineMod = fileMod.readlines()
    version = lineMod[4].split() #Get the line with version value
    version = version[1].replace('"', '').replace(',', '') #Get the version number and remove some values

    if version.endswith('dev'): #Verify if it ends with 'dev'
        version = version.removesuffix('dev')
        suffix = 'dev' #Keep it for later
    else: #If not 'dev', then 'release"
        version = version.removesuffix('release')
        suffix = 'release' #Keep it for later, maybe make automatic release?
    
    actualVersion = ''
    for i in range(4): #Remove the 4 first values (x.x.)
        actualVersion = actualVersion + version[i]
    version = version.removeprefix(actualVersion)
    version = int(version) + 1 #Add 1 to x.x.X

    newVersion = actualVersion + str(version) + suffix #Recompose the updated string
    
    lineMod[4] = f'    version: "{newVersion}",\n' #Change the version in the list
    fileMod.seek(0)
    fileMod.truncate() #Remove all data
    for lineNum, line in enumerate(lineMod):
        fileMod.write(lineMod[lineNum]) #Write one by one the value again
    fileMod.close()