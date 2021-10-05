import matplotlib.pyplot as plt
from pandas import DataFrame
import seaborn as sns
import os
import matplotlib
from scipy.stats import stats


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

    df = DataFrame({"n_owners": list1, "Categoria": list2, "time": list3})
    df = df.replace(to_replace='deserialization', value='msgPack desserialização')
    df = df.replace(to_replace='marshal', value='XML serialização')
    df = df.replace(to_replace='serialization', value='msgPack serialização')
    df = df.replace(to_replace='unmarshal', value='XML desserialização')
    return df


def plot():
    info = get_info_from_file()
    print(info)
    print(info[info["n_owners"] == 100000])
    # x = info[0]
    # y = info[1]
    # sns.lineplot(data=info[info["categoria"] == "marshal"], x="n_owners", y="time", ci="sd")
    # plt.show()

    sns.lineplot(data=info, x="n_owners", y="time", ci="sd", hue="Categoria")
    plt.title("Performance de serialização e desserialização: XML vs msgPack")
    plt.xlabel("Número de instâncias")
    plt.ylabel("Tempo (ms)")
    plt.show()

    info_unmarshal = info[(info["Categoria"] == "XML desserialização")]
    slope, intercept, r_value, p_value, std_err = stats.linregress(info_unmarshal['n_owners'], info_unmarshal['time'])
    sns.regplot(data=info_unmarshal, x="n_owners", y="time", ci=95, scatter=True,
                label="XML (y = %fx + %f)" % (slope, intercept))
    info_deserialization = info[(info["Categoria"] == "msgPack desserialização")]
    slope, intercept, r_value, p_value, std_err = stats.linregress(info_deserialization['n_owners'],
                                                                   info_deserialization['time'])
    sns.regplot(data=info_deserialization, x="n_owners", y="time", ci=95, scatter=True,
                label="msgPack (y = %fx + %f)" % (slope, intercept))
    plt.legend()
    plt.title("Performance de Desserialização: XML vs msgPack")
    plt.xlabel("Número de instâncias")
    plt.ylabel("Tempo (ms)")
    # plt.tight_layout()
    plt.show()

    info_marshal = info[(info["Categoria"] == "XML serialização")]
    slope, intercept, r_value, p_value, std_err = stats.linregress(info_marshal['n_owners'], info_marshal['time'])
    sns.regplot(data=info_marshal, x="n_owners", y="time", ci=95, scatter=True,
                label="XML (y = %fx + %f)" % (slope, intercept))
    info_serialization = info[(info["Categoria"] == "msgPack serialização")]
    slope, intercept, r_value, p_value, std_err = stats.linregress(info_serialization['n_owners'],
                                                                   info_serialization['time'])
    sns.regplot(data=info_serialization, x="n_owners", y="time", ci=95, scatter=True,
                label="msgPack (y = %fx + %f)" % (slope, intercept))
    plt.legend()
    plt.title("Performance de Serialização: XML vs msgPack")
    plt.xlabel("Número de instâncias")
    plt.ylabel("Tempo (ms)")
    # plt.tight_layout()
    plt.show()

    sns.lmplot(data=info[(info["Categoria"] == "XML serialização") | (info["Categoria"] == "msgPack serialização")], x="n_owners",
               y="time", ci=95, hue="Categoria", scatter=False)
    plt.title("Performance de serialização: XML vs msgPack")
    plt.xlabel("Número de instâncias")
    plt.ylabel("Tempo (ms)")
    plt.tight_layout()
    plt.show()
    # print(info)


if __name__ == '__main__':
    plot()
