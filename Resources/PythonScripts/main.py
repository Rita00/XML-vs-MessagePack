import datetime
import random

from faker import Faker
import names

OwnersId = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
            30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56]

OwnersNames = ["Maria", "Jose", "Antonio", "Joao", "Francisco", "Ana", "Luiz", "Paulo", "Carlos", "Manuel", "Pedro",
               "Francisca", "Marcos", "Raimundo", "Sebastiao", "Antonia", "Marcelo", "Jorge", "Marcia", "Geraldo",
               "Adriana", "Sandra", "Fernando", "Fabio", "Roberto", "Marcio", "Edson", "Andre", "Sergio", "Josefa",
               "Patricia", "Daniel", "Rodrigo", "Rafael", "Joaquim", "Vera", "Ricardo", "Eduardo", "Terezinha", "Sonia",
               "Alexandre", "Rita", "Luciana", "Claudio", "Rosa", "Benedito", "Leandro", "Raimunda", "Mario", "Marta",
               "Joana", "Filipa", "Raul", "Anabela", "Jonas", "Nelson"]

OwnersBirth = ["2000/01/12", "1970/01/13", "1976/05/20", "1986/10/20", "1993/01/12", "1960/12/13", "2003/09/04",
               "1970/01/13", "1956/11/30", "1999/04/20", "1970/06/31", "1989/10/01", "2001/12/05", "2000/05/07",
               "1978/06/02", "1997/11/25", "2002/01/23", "1978/05/20", "1987/06/18", "1976/08/20", "1968/09/05"]

# OwnersPhone = [967453621, 927512346, 919067004, 931237750, 960056026, 963316745, 910342278, 910668490, 934564564,
#                932219861, 912274876, 963300768, 913682104, 930699494, 927783539, 967755314, 937399346, 910635579,
#                915871175, 964047195, 933486949, 920356059, 933675324, 912963912, 965085585, 921122718, 938662800,
#                919658770, 968983749, 926113395, 915134235, 930107810, 960908307, 921844896, 930082344, 915514712,
#                961265245, 923992956, 915539083, 933400629, 965628082, 927167727, 918028853, 930436624, 968699423,
#                924901175, 918777319, 935302554, 961336669, 939854678, 917622343, 937765564, 967392875, 934533434,
#                923323123, 965588746]

OwnersPhone = [910000000, 920000000, 930000000, 960000000]

OwnersAddress = ["Rua Brigadeiro Lino Valente", "Rua Cortinhas Fonte", "Estrada Abrantes", "Rua Muro Bacalhoeiros",
                 "Rua Sao Salvador", "Avenida Boavista", "Rua Vasco Gama", "Rua Condes Torre", "Rua São Romao",
                 "Rua Herois Ultramar", "Rua Alto Eira", "Rua Sao Domingos", "Rua Miguel Bombarda", "Avenida Paris",
                 "Avenida Guerra Junqueiro", "Rua Figueiras", "Rua Sarmento Pimentel", "Rua Padre Joao A. L. Ribeiro",
                 "Rua Padua Correia", "Travessa Choupelo"]

PetsId = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
          31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57,
          58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84,
          85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109,
          110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131,
          132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 141, 142, 142, 143, 144, 145, 146, 147, 148, 149, 150]

PetsNames = ["Sherlock", "Diana", "Aladdin", "Bambi", "Fiona", "Hulk", "Charles", "Merlin", "Harry", "Tarzan", "Scooby",
             "Hermione", "Mafalda", "Ariel", "Nemo", "Yoda", "Bolt", "Prenda", "Homer", "Thunder", "Percy", "Will",
             "Gromit", "Matilda", "Groot", "Luca", "Woody", "Sid", "Tony", "Troy", "Danny", "Speed", "Natasha",
             "Stitch", "Gucci", "Karl", "Loui", "Stefan", "Wintour", "Meghan", "Fenty", "Cartier", "Dior", "Portman",
             "Giorgio", "Lirac", "Saint", "Warren", "Celine", "Versace", "Marc", "Fendi", "Elie", "Saab", "Westwood",
             "Pucci", "Wang", "Ballmer", "Françoise", "Gina", "Nancy", "Hilary", "Chrissy", "Shelby", "Barbie",
             "Claire", "Lily", "Andie", "Meredith", "Charlotte", "Aurora", "Sol", "Nile", "Luna", "Gio", "Malu",
             "Minnie", "Joy", "Filipa", "Pedrita", "Tina", "Alegria", "Flor", "Matilde", "Angelina", "Margot", "Ariel",
             "Patty", "Penny", "Bonnie", "Alice", "Dalila", "Bela", "Stella", "Mittens", "Maya", "Xica", "Mel", "Mila",
             "Amora", "Daphne", "Da Vinci", "Dara", "Dolores", "Dakota", "Dadinho", "Delta", "Dexter", "Dinho", "Denis",
             "Dada", "Dudu", "Bernard", "Logan", "Gustaf", "Josh", "Alfredo", "Rob", "Peter", "Lex", "Nolan", "Monet",
             "Phillip", "Mordecai", "Jack", "Jake", "Oliver", "Pietro", "Samurai", "Singer", "Cameron", "Darryl",
             "Norman", "Rosita", "Ash", "Buster", "Guy", "Rambo", "Petris", "Vicente", "Mao", "Eddie", "Koda", "Caleb",
             "Fluffy", "Pirata", "Stallone", "Marshall", "Stuart", "Ronald"]

PetsSpecies = ["Gato", "Cao", "Tartaruga", "Peixe", "Canario", "Ovelha", "Porco", "Hamster", "Cavalo", "Coelho",
               "Chinchila", "Papagaio", "Cobra", "Pato", "Arara"]

PetsGender = ["Feminino", "Masculino"]

PetsWeight = [2.1, 0.5, 3.1, 5.2, 1.5, 1.1, 3.7, 2.7, 1.4, 0.8, 1.4]

PetsBirth = ["2015/01/12", "2020/01/13", "2017/05/20", "2016/10/20", "2021/01/12", "2020/12/13", "2019/09/04",
             "2017/01/13", "2020/11/30", "2021/04/20", "2016/06/31", "2014/10/01", "2018/12/05", "2020/05/07",
             "2015/06/02", "2018/11/25", "2019/01/23", "2021/05/20", "2015/06/18", "2020/08/20", "2020/09/05"]

PetsDescription = ["Animal com crescimento normal", "Aparenta ter artroses, devido ao envelhecimento",
                   "Pelo macio e suave", 'Animal de porte pequeno', "Animal saudavel e que gosta de brincar"]


def random_file(out_file, numOwners):
    fake = Faker()
    f = open(out_file, 'w')
    phone_used = list()
    name_used = list()
    id_pet_used = list()
    name_pet_used = list()

    all_info = list()

    for p in range(numOwners):
        pets_list = list()
        name = names.get_full_name()
        # name = random.choice(OwnersNames)
        while name in name_used:
            name = names.get_full_name()
            # name = random.choice(OwnersNames)
        name_used.append(name)

        # birth_owner = random.choice(OwnersBirth)
        birth_owner = random_date(datetime.date(1960, 1, 1), datetime.date(2005, 1, 1))
        birth_owner = str(birth_owner)
        birth_owner = birth_owner.replace("-", "/")

        phone = random.choice(OwnersPhone) + random.randint(0, 9999999)
        while phone in phone_used:
            phone = random.choice(OwnersPhone) + random.randint(0, 9999999)
        phone_used.append(phone)

        # address = random.choice(OwnersAddress)
        address = fake.address()
        address = address.replace("\n", ", ")
        how_many_pets = random.randint(1, 5)
        for pet in range(how_many_pets):
            id_pet = random.randint(0, 1000000)
            while id_pet in id_pet_used:
                id_pet = random.randint(0, 1000000)
                # id_pet = random.choice(PetsId)
            id_pet_used.append(id_pet)

            name_pet = random.choice(PetsNames)
            # while name_pet in name_pet_used:
            #     name_pet = random.choice(PetsNames)
            # name_pet_used.append(name_pet)

            specie = random.choice(PetsSpecies)

            gender = random.choice(PetsGender)

            weight = random.uniform(0.5, 10.0)

            # birth = random.choice(PetsBirth)
            birth = random_date(datetime.date(2015, 1, 1), datetime.date(2021, 10, 4))
            birth = str(birth)
            birth = birth.replace("-", "/")
            description = random.choice(PetsDescription)

            pets_list.append((id_pet, name_pet, specie, gender, weight, birth, description))

        all_info.append((p, name, birth_owner, phone, address, pets_list))

    for i in all_info:
        f.write("%d#%s#%s#%d#%s#" % (i[0], i[1], i[2], i[3], i[4]))

        # print("%d#%s#%s#%d#%s#" % (i[0], i[1], i[2], i[3], i[4]))

        for n in i[5]:
            f.write("%d+%s+%s+%s+%.2f+%s+%s?" % (n[0], n[1], n[2], n[3], n[4], n[5], n[6]))
            # print("%d+%s+%s+%s+%d+%s+%s?" % (n[0], n[1], n[2], n[3], n[4], n[5], n[6]))
        f.write("\n")
    f.close()


def random_date(start_date, end_date):
    # start_date = datetime.date(2020, 1, 1)
    # end_date = datetime.date(2020, 2, 1)

    time_between_dates = end_date - start_date
    days_between_dates = time_between_dates.days
    random_number_of_days = random.randrange(days_between_dates)

    return start_date + datetime.timedelta(days=random_number_of_days)


if __name__ == '__main__':
    num_owners = 100000
    output_file = str(num_owners) + ".txt"

    random_file(output_file, num_owners)
