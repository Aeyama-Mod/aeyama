import os

class AutoBundles:
    PATH = "./bundles/"
    MAIN_FILE = "bundle.properties"
    OLD_FILE = "bundle_old.properties"
    
    def __init__(self):
        self.lines = self.read_lines(self.PATH + self.MAIN_FILE)
        self.old_lines = self.read_lines(self.PATH + self.OLD_FILE)
        
        #* Modify/Remove/Add lines support
        
        self.write()
        
    def read_lines(self, path):
        lines = []
        with open(path, "r", encoding="utf-8") as file:
            for line_number, line in enumerate(file):
                line = line.rstrip()
                if not line: lines.append({'type': 'empty', 'content': '', 'line_number': line_number})
                elif line.startswith('#'): lines.append({'type': 'comment', 'content': line, 'line_number': line_number})
                else:
                    key, value = line.split('=', 1)
                    lines.append({'type': 'property', 'key': key, 'value': value, 'line_number': line_number})
            file.close()
        return lines

    def write(self):
        for file in os.listdir(self.PATH):
            if not file.startswith("bundle_") and not file.endswith(".properties") and file == self.MAIN_FILE:
                return
            with open(self.PATH + file, 'w', encoding='utf-8') as f:
                for prop in self.lines:
                    match prop["type"]:
                        case 'empty': f.write('\n')
                        case 'comment': f.write(f'{prop["content"]}\n')
                        case 'property': f.write(f'{prop["key"]}={prop["value"]}\n')
                f.close()
    
    def modify(self):
        ...
    
    def add(self):
        ...
        
    def remove(self):
        ...

if __name__ == "__main__":
    auto = AutoBundles()
