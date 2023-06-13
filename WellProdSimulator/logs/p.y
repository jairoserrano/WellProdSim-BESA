import sys
import pandas as pd
import re

# Diccionario para almacenar los datos
data = {}

# Obtén el nombre del archivo de la línea de comandos
filename = sys.argv[1]

# Define un patrón de expresión regular para identificar y separar las variables y sus valores
pattern = re.compile(r'(\w+)=([\w.@/-]*)')

# Abre el archivo de texto y lee línea por línea
with open(filename, 'r') as file:
    for line in file:
        # Verifica si la línea contiene 'wpsViewerAgentGuard:funcExecGuard'
        if 'wpsViewerAgentGuard:funcExecGuard' in line:
            # Encuentra el nombre de la familia campesina
            family_name = re.search('PeasantFamily_\d+', line).group(0)

            # Busca todas las variables y sus valores en la línea
            matches = pattern.findall(line)

            # Crea un diccionario a partir de las variables y sus valores
            line_dict = {var: val for var, val in matches}

            # Almacena el diccionario en el diccionario de datos principal
            # Esto sobrescribirá cualquier entrada anterior para la misma familia campesina
            data[family_name] = line_dict

# Crear un DataFrame de pandas a partir del diccionario
df = pd.DataFrame.from_dict(data, orient='index')

# Convertir el DataFrame a formato Markdown
print(df.to_markdown())

