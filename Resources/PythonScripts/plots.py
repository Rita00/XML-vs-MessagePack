import matplotlib.pyplot as plt
from pandas import DataFrame
import seaborn as sns
import os
import matplotlib


def get_info_from_file():
    # All files ending with .txt

    path = "C:/Users/GU502/PycharmProjects/createRandomPetsOwners/TimeFiles"

    files = os.listdir(path)
    list1 = list()
    list2 = list()
    list3 = list()
    for f in files:
        total_info = f.split('.')
        category = total_info[0].split('_')
        num_owners = category[1]
        category = category[0]

        file = open("TimeFiles/" + f)
        while True:
            line = file.readline()
            if line == '':
                break

            list1.append(int(num_owners))
            list2.append(category)
            list3.append(int(line))

    return DataFrame({"n_owners": list1, "categoria": list2, "time": list3})


def plot():
    info = get_info_from_file()
    print(info)
    print(info[info["n_owners"] == 100000])
    # x = info[0]
    # y = info[1]
    sns.lineplot(data=info[info["categoria"] == "marshal"], x="n_owners", y="time", ci="sd")
    plt.show()
    sns.lineplot(data=info, x="n_owners", y="time", ci="sd", hue="categoria")
    plt.show()
    sns.lineplot(data=info[(info["categoria"] == "marshal") | (info["categoria"] == "serialization")], x="n_owners",
                 y="time", ci="sd", hue="categoria")
    plt.title("Performance de serialização: XML vs msgPack")
    plt.xlabel("Número de instâncias")
    plt.ylabel("Tempo (ms)")
    plt.show()
    # print(info)


if __name__ == '__main__':
    plot()
