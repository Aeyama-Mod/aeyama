import os
import io

class AutoBundles:
    PATH = "./bundles/"
    MAIN_FILE = "bundle.properties"
    OLD_FILE = "bundle_old.properties"
    
    def __init__(self):
        self.lines = self.read_lines(self.PATH + self.MAIN_FILE)
        self.old_lines = self.read_lines(self.PATH + self.OLD_FILE)
        self.files = self.open_files()        
        
        self.modify()
        self.close_files()
        
    def modify(self):
        changes = {'modified': [], 'added': [], 'removed': []}
        max_lines = max(len(self.lines), len(self.old_lines))
        for line_number in range(max_lines):
            if line_number >= len(self.lines): #Removed line
                changes['removed'].append(self.old_lines[line_number])
            elif line_number >= len(self.old_lines): #Added line
                changes['added'].append(self.lines[line_number])
            else:
                line = self.lines[line_number]
                old_line = self.old_lines[line_number]
                if line['type'] == 'property' and old_line['type'] == 'property':
                    if line['name'] != old_line['name'] or line['description'] != old_line['description']:
                        changes['modified'].append(line)
                elif line['type'] == 'comment' and old_line['type'] == 'comment' and line['content'] != old_line['content']:
                    changes['modified'].append(line)
        
        for file in self.files:
            lines = self.read_lines(file.name)
            
            for change in changes['removed']:
                lines = [line for line in lines if line['type'] != change['type']]
            
            for change in changes['modified']:
                for line_number, line in enumerate(lines):
                    if line['type'] == change['type'] and (line['name'] == change['name'] or line['description'] == change['description']):
                        lines[line_number] = change
                    if line['type'] == 'comment' and change['type'] == 'comment' and line['content'] == change['content']:
                        lines[line_number]['content'] = change['content']
            
            for change in changes['added']:
                lines.append(change)
            
            lines.sort(key=lambda p: p['line_number'])
            for line in lines:
                match line["type"]:
                    case 'empty': file.write('\n')
                    case 'comment': file.write(f'{line["content"]}\n')
                    case 'property': file.write(f'{line["name"]}={line["description"]}\n')
    
    def open_files(self) -> list[io.TextIOWrapper]:
        files = []
        for file in os.listdir(self.PATH):
            if file == self.MAIN_FILE:
                continue
            files.append(open(self.PATH + file, 'r+', encoding='utf-8'))
        return files
    
    def close_files(self):
        for file in self.files:
            self.files.remove(file)
            file.close()
            
    def read_lines(self, path):
        lines = []
        with open(path, "r", encoding="utf-8") as file:
            for line_number, line in enumerate(file):
                line = line.rstrip()
                if not line: lines.append({'type': 'empty', 'content': '', 'line_number': line_number})
                elif line.startswith('#'): lines.append({'type': 'comment', 'content': line, 'line_number': line_number})
                else:
                    name, description = line.split('=', 1)
                    lines.append({'type': 'property', 'name': name, 'description': description, 'line_number': line_number})
            file.close()
        return lines


if __name__ == "__main__":
    auto = AutoBundles()
