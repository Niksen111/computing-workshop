import matplotlib.pyplot as plt
import csv

# Функция для чтения данных из CSV файла
def read_csv(file_path):
    x = []
    y1 = []
    y2 = []

    with open(file_path, newline='') as csvfile:
        csvreader = csv.reader(csvfile)
        for row in csvreader:
            if len(row) == 3:  # Если файл содержит три столбца
                x.append(float(row[0]))
                y1.append(float(row[1]))
                y2.append(float(row[2]))
            elif len(row) == 2:  # Если файл содержит два столбца
                x.append(float(row[0]))
                y1.append(float(row[1]))
    return x, y1, y2

# Построение первого графика
def plot_first_graph(csv_file):
    x, y1, y2 = read_csv(csv_file)
    plt.figure()
    plt.xscale('log', base=10)
    plt.yscale('log', base=10)
    plt.plot(x, y1, label='Приближенные значения')
    plt.plot(x, y2, label='Реальные значения')
    plt.xlabel('X')
    plt.ylabel('Y')

    plt.legend()
#     plt.title('Решение')
    plt.grid(True)
    plt.show()

# Построение второго графика
def plot_second_graph(csv_file):
    x, y, _ = read_csv(csv_file)  # Используем только два столбца
    plt.figure()
    plt.xscale('log', base=10)
    plt.yscale('log', base=10)
    plt.plot(x, y, label='Значение ошибки')
    plt.xlabel('Количество делений')
    plt.ylabel('Значение ошибки')
    plt.legend()
    plt.title('График 2')
    plt.grid(True)
    plt.show()

plot_first_graph('src/main/resources/data1.csv')
plot_second_graph('src/main/resources/data2.csv')