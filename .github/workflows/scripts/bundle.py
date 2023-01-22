### Script by 🌟 Jojo#6430
import os

src = './bundles' #Path to the bundles files

def update():
    with open(f'{src}/bundle.properties', 'r+')as mainFile, open(f'{src}/bundleOld.properties', 'r+') as oldFile:
        mainLines = mainFile.readlines()
        oldLines = oldFile.readlines()
        for folder_name, subfolders, filenames in os.walk(f'{src}'):
            for filename in filenames:
                if filename != 'bundle.properties' and filename != 'bundleOld.properties' and filename.endswith('.properties'):
                    with open(f'{src}/{filename}', encoding = 'utf-8', mode = 'r+') as file:
                        fileLines = file.readlines()
                        for lineNumber, line in enumerate(mainLines):
                            if mainLines[lineNumber] != oldLines[lineNumber]:
                                fileLines[lineNumber] = mainLines[lineNumber]
                        file.seek(0)
                        file.truncate()
                        for lineNumber, line in enumerate(fileLines):
                            file.writelines(fileLines[lineNumber])
            
        oldFile.seek(0)
        oldFile.truncate()
        for lineNumber, line in enumerate(oldLines):
            if oldLines[lineNumber] != mainLines[lineNumber]:
                oldLines[lineNumber] = mainLines[lineNumber]
        for lineNumber, line in enumerate(oldLines):
            oldFile.writelines(oldLines[lineNumber])

def add():
    with open(f'{src}/bundle.properties', 'r+')as mainFile, open(f'{src}/bundleOld.properties', 'r+') as oldFile:
        mainLines = mainFile.readlines()
        oldLines = oldFile.readlines()
        oldNumber in enumerate(oldLines)
        for folder_name, subfolders, filenames in os.walk(f'{src}'):
            for filename in filenames:
                if filename != 'bundle.properties' and filename != 'bundleOld.properties' and filename.endswith('.properties'):
                    with open(f'{src}/{filename}', encoding = 'utf-8', mode = 'r+') as file:
                        fileLines = file.readlines()
                        for mainNumber, mainLine in enumerate(mainLines):
                            if mainNumber <= oldNumber:
                                if mainLines[mainNumber] != oldLines[mainNumber]:
                                    fileLines[mainNumber] = mainLines[mainNumber]
                            elif mainNumber > oldNumber:
                                fileLines.append(mainLines[mainNumber])
                        file.seek(0)
                        file.truncate()
                        for lineNumber, line in enumerate(fileLines):
                            file.writelines(fileLines[lineNumber])

        for lineNumber, line in enumerate(mainLines):
            if lineNumber <= oldNumber:
                if oldLines[lineNumber] != mainLines[lineNumber]:
                    oldLines[lineNumber] = mainLines[lineNumber]
            elif lineNumber > oldNumber:
                oldLines.append(mainLines[lineNumber])
        oldFile.seek(0)
        oldFile.truncate()
        for lineNumber, line in enumerate(mainLines):
            oldFile.writelines(oldLines[lineNumber])

def remove():
    with open(f'{src}/bundle.properties', 'r+')as mainFile, open(f'{src}/bundleOld.properties', 'r+') as oldFile:
        mainLines = mainFile.readlines()
        oldLines = oldFile.readlines()
        mainNumber in enumerate(oldLines)
        for folder_name, subfolders, filenames in os.walk(f'{src}'):
            for filename in filenames:
                if filename != 'bundle.properties' and filename != 'bundleOld.properties' and filename.endswith('.properties'):
                    with open(f'{src}/{filename}', encoding = 'utf-8', mode = 'r+') as file:
                        fileLines = file.readlines()
                        i = -1
                        for oldNumber, oldLine in enumerate(oldLines):
                            if oldNumber <= mainNumber:
                                if mainLines[oldNumber] != oldLines[oldNumber]:
                                    fileLines[oldNumber] = mainLines[oldNumber]
                            elif oldNumber > mainNumber:
                                i+=1
                                fileLines.pop(oldNumber-i)
                        file.seek(0)
                        file.truncate()
                        for lineNumber, line in enumerate(fileLines):
                            file.writelines(fileLines[lineNumber])

        i = -1
        for lineNumber, line in enumerate(oldLines):
            if lineNumber <= mainNumber:
                if oldLines[lineNumber] != mainLines[lineNumber]:
                    oldLines[lineNumber] = mainLines[lineNumber]
            elif lineNumber > mainNumber:
                i+=1
                oldLines.pop(lineNumber-i)
        oldFile.seek(0)
        oldFile.truncate()
        for lineNumber, line in enumerate(mainLines):
            oldFile.writelines(oldLines[lineNumber])

with open(f'{src}/bundle.properties')as mainFile, open(f'{src}/bundleOld.properties') as oldFile:
    mainLines = mainFile.readlines()
    oldLines = oldFile.readlines()
    for mainNumber, mainLine in enumerate(mainLines):
        pass
    for oldNumber, oldLine in enumerate(oldLines):
        pass

    if mainNumber == oldNumber:
        print('No new line/removed line, check for modified lines')
        update()
    elif mainNumber > oldNumber:
        print('1 or more line was added, check for it, add them then update lines')
        add()
    elif mainNumber < oldNumber:
        print('1 or more line was removed, check for it, remove them then update lines')
        remove()