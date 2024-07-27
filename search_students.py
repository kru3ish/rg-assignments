import sqlite3

def connect_db():
    return sqlite3.connect('assignment.db')

def search_students(search_str):
    conn = connect_db()
    cursor = conn.cursor()
    
    query = f"SELECT * FROM students WHERE name LIKE ?"
    cursor.execute(query, ('%' + search_str + '%',))
    
    rows = cursor.fetchall()
    
    if rows:
        total_marks = 0
        print("Results:")
        for row in rows:
            print(f"Name: {row[0]}, Marks: {row[1]}")
            total_marks += row[1]
        print(f"Total Marks: {total_marks}")
        print(f"Average Marks: {total_marks / len(rows)}")
    else:
        print("No results found.")
    
    conn.close()

if __name__ == "__main__":
    while True:
        search_str = input("Enter a search string: ").strip()
        if not search_str:
            print("Please enter a search string")
            continue
        search_students(search_str)