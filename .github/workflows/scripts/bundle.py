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
        
        self.write()
        self.close_files()
        
    def modify(self):
        changes = {'modified': [], 'added': [], 'removed': []}
        for line, old_line in zip(self.lines, self.old_lines):
            if line['type'] == 'property' and old_line['type'] == 'property':
                if line['description'] != old_line['description']:
                    changes['modified'].append(line['description'])
                if line['name'] != old_line['name']:
                    changes['modified'].append(line['name'])
                    
    
    def open_files(self) -> list[io.TextIOWrapper]:
        files = []
        for file in os.listdir(self.PATH):
            files.append(open(self.PATH + file, 'w', encoding='utf-8'))
        return files
    
    def close_files(self):
        for file in self.files:
            self.files.remove(file)
            file.close()
            
    def write(self):
        for file in self.files:
            for line in self.lines:
                match line["type"]:
                    case 'empty': file.write('\n')
                    case 'comment': file.write(f'{line["content"]}\n')
                    case 'property': file.write(f'{line["name"]}={line["description"]}\n')
        
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
