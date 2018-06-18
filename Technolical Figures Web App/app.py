# Note: Small portions of app.py and much of createDB.py were sourced from
# https://pythonspot.com/login-authentication-with-flask/
# All other code is original

from flask import Flask
from flask import Flask, flash, redirect, render_template, request, session, abort
import os
from sqlalchemy.orm import sessionmaker
from sqlalchemy import create_engine
from createDB import *
import cgitb
import cgi
import sqlite3
import hashlib

engine = create_engine('sqlite:///accounts.db', echo=True)
signedInUser = "" #Used to display signed in user in navigation bar (uses Jinja2 template engine for use in HTML)
loggedIn = 0
incorrect_password = 0
no_user_found = 0
already_registered = 0
no_match = 0
verif_mismatch = 0;

app = Flask(__name__)

@app.route('/') #Each @app.route() refers to a web address or script on the server
def home(): #Navigate to the home page
    loggedInNow = loggedIn
    if not session.get('logged_in'):
        return render_template('index.html')
    else:
        return homeLoggedIn()

@app.route('/signed_in')
def homeLoggedIn():
    user = signedInUser #Required for Jinja2 HTML modification, along with **locals() below
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()

    liked_steve = 0
    table_name = user + "_likes"

    like_list = []
    for i in c.execute('SELECT likes FROM ' + table_name):
        like_list.append(str(i)[3:-3])

    if "steve_jobs" in like_list:
        liked_steve = 1;

    liked_reed = 0

    like_list = []
    for i in c.execute('SELECT likes FROM ' + table_name):
        like_list.append(str(i)[3:-3])

    if "reed_hastings" in like_list:
        liked_reed = 1;

    liked_mark = 0

    like_list = []
    for i in c.execute('SELECT likes FROM ' + table_name):
        like_list.append(str(i)[3:-3])

    if "mark_zuckerberg" in like_list:
        liked_mark = 1;

    liked_jcr = 0
    like_list = []
    for i in c.execute('SELECT likes FROM ' + table_name):
        like_list.append(str(i)[3:-3])

    if "jcr_licklider" in like_list:
        liked_jcr = 1;

    user_list = []
    for i in c.execute('SELECT username FROM users'):
        user_list.append(str(i)[3:-3])

    return render_template('index.html', **locals())

@app.route('/signup')
def sign(): #Navigate to the signup page, only if not already logged in
    if not session.get('logged_in'): #Prevents users from accessing the signup page if they are already logged in
        user = signedInUser
        global no_match
        verif_failed = no_match
        global already_registered
        not_avail = already_registered
        return render_template('signup.html', **locals())
    else: 
        return home()

@app.route('/create_account', methods=['POST'])
def signup(): #Create account by filling in accounts.db
    if not os.path.isfile('accounts.db'): #If database doesn't exist, run createDB.py to create accounts.db
        return createDB.py

    account_form = cgi.FieldStorage()
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    hashPass = "" #Used to store hashed password
    
    name = str(request.form['name'])
    password = str(request.form['password'])
    verifPass = str(request.form['vpass'])

    for n in c.execute('SELECT username FROM users'):
        if name in n: #If the entered username is already in the database, it is not available
            global already_registered
            already_registered = 1
            global no_match
            no_match = 0
            return sign()
    
    if password != verifPass:
        global no_match
        no_match = 1
        global already_registered
        already_registered = 0
        return sign()

    global no_match
    no_match = 0
    global already_registered
    already_registered = 0

    Session = sessionmaker(bind=engine) #Creates a Session in the database
    session = Session()

    hashPass = hashlib.md5(password).hexdigest() #Hashes entered password for security
    
    user = User(name,hashPass) #Adds new user to users table in accounts.db
    session.add(user)

    #Creates and pre-fills 'info' table in accounts.db for use in "My Account"
    c.execute(''' 
        CREATE TABLE IF NOT EXISTS info
        (username varchar(100) primary key, fullname varchar(100),
        email varchar(100), age varchar(100), gender varchar(100))
        ''')
    c.execute('INSERT into info values (?,?,?,?,?)',
        (name, "Not yet configured", "Not yet configured", "Not yet configured", "Not yet configured"))

    table_name = name + "_likes"
    table_name2 = "shared_with_" + name
    table_name3 = name + "_page"
    table_name4 = "user_pages_shared_with_" + name
    table_name5 = name + "_page_comments"

    #Create ~likes table for each user to store liked pages
    c.execute('CREATE TABLE IF NOT EXISTS ' + '"' + table_name + '"' + '(likes varchar(100) primary key)')

    #Create shared_with~ table for each user to store pages shared with them
    c.execute('CREATE TABLE IF NOT EXISTS ' + '"' + table_name2 + '"' + '(shares varchar(100) primary key, shared_by varchar(100))')

    c.execute('CREATE TABLE IF NOT EXISTS ' + '"' + table_name4 + '"' + '(shares varchar(100) primary key, shared_by varchar(100))')

    c.execute('CREATE TABLE IF NOT EXISTS ' + '"' + table_name5 + '"' + '(id INTEGER primary key AUTOINCREMENT, comment varchar(1000), comment_by varchar(100))')

    c.execute('CREATE TABLE IF NOT EXISTS ' + '"' + table_name3 + '"' + '''
        (id INTEGER primary key, title varchar(100), h2_1 varchar(100),
        h2_2 varchar(100), h2_3 varchar(100), h3_1 varchar(100),
        h3_2 varchar(100), h3_3 varchar(100), h3_4 varchar(100),
        h3_5 varchar(100), h3_6 varchar(100), h3_7 varchar(100),
        p1 varchar(10000), p2 varchar(10000), p3 varchar(10000),
        p4 varchar(10000), p5 varchar(10000), p6 varchar(10000),
        p7 varchar(10000), p8 varchar(10000), p9 varchar(10000),
        p10 varchar(10000), p11 varchar(10000), p12 varchar(10000),
        p13 varchar(10000), p14 varchar(10000), p15 varchar(10000),
        cap1 varchar(1000), cap2 varchar(1000), cap3 varchar(1000),
        cap4 varchar(1000), cap5 varchar(1000))
    ''')

    title = "*Title*"
    h2_1 = "*Large heading 1*"
    h2_2 = "*Large heading 2*"
    h2_3 = "*Large heading 3*"
    h3_1 = "*Heading 1*"
    h3_2 = "*Heading 2*"
    h3_3 = "*Heading 3*"
    h3_4 = "*Heading 4*"
    h3_5 = "*Heading 5*"
    h3_6 = "*Heading 6*"
    h3_7 = "*Heading 7*"
    p1 = "*Paragraph 1*"
    p2 = "*Paragraph 2*"
    p3 = "*Paragraph 3*"
    p4 = "*Paragraph 4*"
    p5 = "*Paragraph 5*"
    p6 = "*Paragraph 6*"
    p7 = "*Paragraph 7*"
    p8 = "*Paragraph 8*"
    p9 = "*Paragraph 9*"
    p10 = "*Paragraph 10*"
    p11 = "*Paragraph 11*"
    p12 = "*Paragraph 12*"
    p13 = "*Paragraph 13*"
    p14 = "*Paragraph 14*"
    p15 = "*Paragraph 15*"
    cap1 = "*Image caption 1*"
    cap2 = "*Image caption 2*"
    cap3 = "*Image caption 3*"
    cap4 = "*Image caption 4*"
    cap5 = "*Image caption 5*"

    c.execute('INSERT into ' + '"' + table_name3 + '"' + ' values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)',
        (1, title, h2_1, h2_2, h2_3, h3_1, h3_2, h3_3, h3_4, h3_5, h3_6, h3_7, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, cap1, cap2, cap3, cap4, cap5))

    conn.commit()
    conn.close()

    global signedInUser
    signedInUser = name
     
    session.commit()
     
    session.commit()
    return signup_login(name, password) #Login the user automatically after creating new account
 
@app.route('/signedin', methods=['POST'])
def signin(): #Validate user credentials and login if applicable
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    
    hashPass = ""
    username = str(request.form['name'])
    password = str(request.form['password'])
    hashPass = hashlib.md5(password).hexdigest() #Hash entered password to compare with database password (which was previously hashed, then stored)
 
    Session = sessionmaker(bind=engine) #Session-based login; allows user to remain logged in after browser closes
    s = Session()
    query = s.query(User).filter(User.username.in_([username]), User.password.in_([hashPass])) #Validate user credentials
    result = query.first()
    if result: #If credentials are successfully verified, login
        session['logged_in'] = True
        global loggedIn
        loggedIn = 1
        global incorrect_password
        incorrect_password = 0
        global no_user_found
        no_user_found = 0
        global signedInUser
        signedInUser = username
        return home()
    else: #Otherwise, determine what caused credentials to mismatch and display
        userList = []
        for i in c.execute('SELECT username FROM users'):
            userList.append(str(i)[3:-3])
        if username in userList: #If the entered username is present in the database, mismatch was caused by incorrect password
            global no_user_found
            no_user_found = 0
            global incorrect_password
            incorrect_password = 1
            return login()
        else: #otherwise, mismatch was caused by a username that is not associated with an account
            global incorrect_password
            incorrect_password = 0
            global no_user_found
            no_user_found = 1
            return login()

@app.route('/signup_login', methods=['POST'])
def signup_login(name, password): #Logs new users in immediately and automatically after successful registration
    hashPass = ""
    username = name
    password = password
    hashPass = hashlib.md5(password).hexdigest()
 
    Session = sessionmaker(bind=engine)
    s = Session()
    query = s.query(User).filter(User.username.in_([username]), User.password.in_([hashPass]) )
    session['logged_in'] = True
    return home() #Mismatched credentials will never occur directly after signup

@app.route('/login')
def login(): #Navigate to the login page
    if not session.get('logged_in'): #Prevents users from accessing the login page if they are already logged in
        user = signedInUser
        global incorrect_password
        wrong_pass = incorrect_password
        global no_user_found
        no_user = no_user_found
        return render_template('login.html', **locals())
    else: return home()

@app.route('/steve_jobs')
def steve(): #Navigate to Steve Jobs
    user = signedInUser
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()

    if session.get('logged_in'):
        liked = 0
        table_name = user + "_likes"
        user_list = []

        like_list = []
        for i in c.execute('SELECT likes FROM ' + table_name):
            like_list.append(str(i)[3:-3])

        if "steve_jobs" in like_list:
            liked = 1;

        steve_likes = ""
        for j in likessteve(): #Create string of users who like this page, for printing using JavaScript and Jinja2
            steve_likes = steve_likes + j + ", "

        steve_likes = steve_likes[:-2]

        for j in c.execute('SELECT username FROM users'):
            user_list.append(str(j)[3:-3])

        comment_list = []
        comment_by_list = []
        comment_id = 1
        comment_list_temp =[]

        #Used by JavaScript to print comments; this particular way of doing it keeps the comments in order

        c.execute('CREATE TABLE IF NOT EXISTS steve_comments (id INTEGER primary key AUTOINCREMENT, comment varchar(1000), comment_by varchar(100))')

        for i in c.execute('SELECT comment FROM steve_comments'):
            comment_list_temp.append(str(i)[3:-3])

        while comment_id <= len(comment_list_temp):
            for i in c.execute('SELECT comment FROM steve_comments WHERE id=?', (comment_id,)):
                comment_list.append(str(i)[3:-3])
            comment_id = comment_id + 1

        comment_id = 1;
        while comment_id <= len(comment_list_temp):
            for i in c.execute('SELECT comment_by FROM steve_comments WHERE id=?', (comment_id,)):
                comment_by_list.append(str(i)[3:-3])
            comment_id = comment_id + 1

    return render_template('stevejobs.html', **locals())

@app.route('/reed_hastings')
def reed(): #Navigate to Reed Hastings
    user = signedInUser
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    user_list = []

    if session.get('logged_in'):
        liked = 0
        table_name = user + "_likes"

        like_list = []
        for i in c.execute('SELECT likes FROM ' + table_name):
            like_list.append(str(i)[3:-3])

        if "reed_hastings" in like_list:
            liked = 1;

        reed_likes = ""
        for j in likesreed():
            reed_likes = reed_likes + j + ", "

        reed_likes = reed_likes[:-2]

        for j in c.execute('SELECT username FROM users'):
            user_list.append(str(j)[3:-3])

        comment_list = []
        comment_by_list = []
        comment_id = 1
        comment_list_temp =[]

        c.execute('CREATE TABLE IF NOT EXISTS reed_comments (id INTEGER primary key AUTOINCREMENT, comment varchar(1000), comment_by varchar(100))')

        for i in c.execute('SELECT comment FROM reed_comments'):
            comment_list_temp.append(str(i)[3:-3])

        while comment_id <= len(comment_list_temp):
            for i in c.execute('SELECT comment FROM reed_comments WHERE id=?', (comment_id,)):
                comment_list.append(str(i)[3:-3])
            comment_id = comment_id + 1

        comment_id = 1;
        while comment_id <= len(comment_list_temp):
            for i in c.execute('SELECT comment_by FROM reed_comments WHERE id=?', (comment_id,)):
                comment_by_list.append(str(i)[3:-3])
            comment_id = comment_id + 1

    return render_template('reedhastings.html', **locals())

@app.route('/mark_zuckerberg')
def mark(): #Navigate to Mark Zuckerberg
    user = signedInUser
    user = signedInUser
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    user_list = []

    if session.get('logged_in'):
        liked = 0
        table_name = user + "_likes"

        like_list = []
        for i in c.execute('SELECT likes FROM ' + table_name):
            like_list.append(str(i)[3:-3])

        if "mark_zuckerberg" in like_list:
            liked = 1;

        mark_likes = ""
        for j in likesmark():
            mark_likes = mark_likes + j + ", "

        mark_likes = mark_likes[:-2]

        for j in c.execute('SELECT username FROM users'):
            user_list.append(str(j)[3:-3])

        comment_list = []
        comment_by_list = []
        comment_id = 1
        comment_list_temp =[]

        c.execute('CREATE TABLE IF NOT EXISTS mark_comments (id INTEGER primary key AUTOINCREMENT, comment varchar(1000), comment_by varchar(100))')

        for i in c.execute('SELECT comment FROM mark_comments'):
            comment_list_temp.append(str(i)[3:-3])

        while comment_id <= len(comment_list_temp):
            for i in c.execute('SELECT comment FROM mark_comments WHERE id=?', (comment_id,)):
                comment_list.append(str(i)[3:-3])
            comment_id = comment_id + 1

        comment_id = 1;
        while comment_id <= len(comment_list_temp):
            for i in c.execute('SELECT comment_by FROM mark_comments WHERE id=?', (comment_id,)):
                comment_by_list.append(str(i)[3:-3])
            comment_id = comment_id + 1

    return render_template('markzuckerberg.html', **locals())

@app.route('/jcr_licklider')
def jcr(): #Navigate to JCR Licklider
    user = signedInUser
    user = signedInUser
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    user_list =[]

    if session.get('logged_in'):
        liked = 0
        table_name = user + "_likes"

        like_list = []
        for i in c.execute('SELECT likes FROM ' + table_name):
            like_list.append(str(i)[3:-3])

        if "jcr_licklider" in like_list:
            liked = 1;

        jcr_likes = ""
        for j in likesjcr():
            jcr_likes = jcr_likes + j + ", "

        jcr_likes = jcr_likes[:-2]

        for j in c.execute('SELECT username FROM users'):
            user_list.append(str(j)[3:-3])

        comment_list = []
        comment_by_list = []
        comment_id = 1
        comment_list_temp =[]

        c.execute('CREATE TABLE IF NOT EXISTS jcr_comments (id INTEGER primary key AUTOINCREMENT, comment varchar(1000), comment_by varchar(100))')

        for i in c.execute('SELECT comment FROM jcr_comments'):
            comment_list_temp.append(str(i)[3:-3])

        while comment_id <= len(comment_list_temp):
            for i in c.execute('SELECT comment FROM jcr_comments WHERE id=?', (comment_id,)):
                comment_list.append(str(i)[3:-3])
            comment_id = comment_id + 1

        comment_id = 1;
        while comment_id <= len(comment_list_temp):
            for i in c.execute('SELECT comment_by FROM jcr_comments WHERE id=?', (comment_id,)):
                comment_by_list.append(str(i)[3:-3])
            comment_id = comment_id + 1

    return render_template('licklider.html', **locals())
 
@app.route("/logout")
def logout(): #Logout of current signed-in account
    session['logged_in'] = False
    global loggedIn
    loggedIn = 0
    return home()

@app.route("/my_account")
def myaccount(): #Navigate to My Account page; reads from info table in accounts.db to determine content of myaccount.html template (Jinja2)
    if session.get('logged_in'): #Prevents users from reaching the My Account page without signing in
        conn = sqlite3.connect('accounts.db')
        c = conn.cursor()

        user = signedInUser
        name = signedInUser

        #Reads info table in accounts.db based on signed-in user to populate My Account information
        fullName = ""
        for i in c.execute('SELECT fullname FROM info WHERE username = ' + '"' + name + '"'):
            fullName = str(i)[3:-3] #Slicing to remove u'', from table query (e.g. u'james',)

        age = ""
        for i in c.execute('SELECT age FROM info WHERE username = ' + '"' + name + '"'):
            age = str(i)[3:-3]

        gender = ""
        for i in c.execute('SELECT gender FROM info WHERE username = ' + '"' + name + '"'):
            gender = str(i)[3:-3]

        email = ""
        for i in c.execute('SELECT email FROM info WHERE username = ' + '"' + name + '"'):
            email = str(i)[3:-3]

        return render_template('myaccount.html', **locals())
    else:
        return home()

@app.route("/configure_account", methods=['POST'])
def configureaccount(): #Updates info table in accounts.db using the web form in editaccount.html, then navigates to My Account
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()

    user = signedInUser

    fullName = str(request.form['name'])
    if(fullName == ""):
        for i in c.execute('SELECT fullname FROM info WHERE username = ' + '"' + user + '"'):
            fullName = str(i)[3:-3]

    email = str(request.form['email'])
    if(email == ""):
        for i in c.execute('SELECT email FROM info WHERE username = ' + '"' + user + '"'):
            email = str(i)[3:-3]

    age = str(request.form['age'])
    if(age == ""):
        for i in c.execute('SELECT age FROM info WHERE username = ' + '"' + user + '"'):
            age = str(i)[3:-3]

    gender = str(request.form['gender'])
    if(gender == ""):
        for i in c.execute('SELECT gender FROM info WHERE username = ' + '"' + user + '"'):
            gender = str(i)[3:-3]

    c.execute('''
        UPDATE info
        SET fullname=?, email=?, age=?, gender=?
        WHERE username=?
    ''', (fullName, email, age, gender, user))

    conn.commit()
    conn.close()
    return render_template('myaccount.html', **locals())

@app.route("/edit_account")
def editaccount(): #Navigate to Edit Account page
    user = signedInUser
    return render_template('editaccount.html', **locals())

@app.route("/my_likes")
def mylikes(): #Navigate to My Likes page; reads from logged-in user's ~likes table to inject into HTML
    user = signedInUser
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    like_list = []
    table_name = user + "_likes"

    for i in c.execute('SELECT likes FROM ' + table_name): #mylikes.html prints each entry of like_list
        like_list.append(str(i)[3:-3])

    return render_template('mylikes.html', **locals())

@app.route("/shared_with_me")
def myshares():
    user = signedInUser
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    share_list = [] #Used by Jinja2 template engine to print to Shared With Me page
    share_list2 = []
    shared_by_list = []
    table_name = "shared_with_" + user
    table_name2 = "user_pages_shared_with_" + user

    for i in c.execute('SELECT shares FROM ' + table_name): #Scan user's share_with~ stable and inserrt each page into the list
        share_list.insert(0, str(i)[3:-3])

    for i in c.execute('SELECT shares FROM ' + table_name2): #Scan user's share_with~ stable and inserrt each page into the list
        share_list2.insert(0, str(i)[3:-3])

    #The following loops search the user's table for each page and add its matching shared_by value to the shared_by_list

    for i in c.execute('SELECT shared_by FROM ' + '"' + table_name + '"' + ' WHERE shares=?', ("steve_jobs",)):
        shared_by_list.append(str(i)[3:-3])
    for i in c.execute('SELECT shared_by FROM ' + '"' + table_name + '"' + ' WHERE shares=?', ("reed_hastings",)):
        shared_by_list.append(str(i)[3:-3])
    for i in c.execute('SELECT shared_by FROM ' + '"' + table_name + '"' + ' WHERE shares=?', ("mark_zuckerberg",)):
        shared_by_list.append(str(i)[3:-3])
    for i in c.execute('SELECT shared_by FROM ' + '"' + table_name + '"' + ' WHERE shares=?', ("jcr_licklider",)):
        shared_by_list.append(str(i)[3:-3])

    return render_template('sharedwithme.html', **locals())

@app.route("/like_steve")
def likesteve(): #The four like methods insert liked pages into each user's ~likes table to be read by the My Likes page/method
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    c2 = conn.cursor()
    user = signedInUser
    table_name = user + "_likes"

    like_list = []
    for i in c.execute('SELECT likes FROM ' + table_name):
        like_list.append(str(i)[3:-3])

    if "steve_jobs" not in like_list:
        c.execute('INSERT into ' + '"' + table_name + '"' + ' values (?)', ("steve_jobs",)) #Likes page
    else:
        c.execute('DELETE from ' + '"' + table_name + '"' + ' WHERE likes=?', ("steve_jobs",)) #Allows for un-liking

    conn.commit()
    conn.close()
    return ('', 204)

def likessteve(): #The four "likes~" methods, which follow each page's "like~" method, use a nested for loop to find all users that like the respective page
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    c2 = conn.cursor()
    user = signedInUser
    like_list = []

    name = ""
    name_table = ""

    for j in c.execute('SELECT username FROM users'): #Cycle through users and find thier ~likes table
        name_table = str(j)[3:-3] + "_likes"
        name = str(j)[3:-3]
        for n in c2.execute('SELECT likes FROM ' + name_table): #Search that table for the desired page and add user to list if page is found
            if str(n)[3:-3] == "steve_jobs":
                like_list.append(name)

    return like_list

@app.route("/like_reed")
def likereed():
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    user = signedInUser
    table_name = user + "_likes"

    like_list = []
    for i in c.execute('SELECT likes FROM ' + table_name):
        like_list.append(str(i)[3:-3])

    if "reed_hastings" not in like_list:
        c.execute('INSERT into ' + '"' + table_name + '"' + ' values (?)', ("reed_hastings",))
    else:
        c.execute('DELETE from ' + '"' + table_name + '"' + ' WHERE likes=?', ("reed_hastings",))

    conn.commit()
    conn.close()
    return ('', 204)

def likesreed():
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    c2 = conn.cursor()
    user = signedInUser
    like_list = []

    name = ""
    name_table = ""

    for j in c.execute('SELECT username FROM users'):
        name_table = str(j)[3:-3] + "_likes"
        name = str(j)[3:-3]
        for n in c2.execute('SELECT likes FROM ' + name_table):
            if str(n)[3:-3] == "reed_hastings":
                like_list.append(name)

    return like_list

@app.route("/like_mark")
def likemark():
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    user = signedInUser
    table_name = user + "_likes"

    like_list = []
    for i in c.execute('SELECT likes FROM ' + table_name):
        like_list.append(str(i)[3:-3])

    if "mark_zuckerberg" not in like_list:
        c.execute('INSERT into ' + '"' + table_name + '"' + ' values (?)', ("mark_zuckerberg",))
    else:
        c.execute('DELETE from ' + '"' + table_name + '"' + ' WHERE likes=?', ("mark_zuckerberg",))

    conn.commit()
    conn.close()
    return ('', 204)

def likesmark():
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    c2 = conn.cursor()
    user = signedInUser
    like_list = []

    name = ""
    name_table = ""

    for j in c.execute('SELECT username FROM users'):
        name_table = str(j)[3:-3] + "_likes"
        name = str(j)[3:-3]
        for n in c2.execute('SELECT likes FROM ' + name_table):
            if str(n)[3:-3] == "mark_zuckerberg":
                like_list.append(name)

    return like_list

@app.route("/like_jcr")
def likejcr():
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    user = signedInUser
    table_name = user + "_likes"

    like_list = []
    for i in c.execute('SELECT likes FROM ' + table_name):
        like_list.append(str(i)[3:-3])

    if "jcr_licklider" not in like_list:
        c.execute('INSERT into ' + '"' + table_name + '"' + ' values (?)', ("jcr_licklider",))
    else:
        c.execute('DELETE from ' + '"' + table_name + '"' + ' WHERE likes=?', ("jcr_licklider",))

    conn.commit()
    conn.close()
    return ('', 204)

def likesjcr():
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    c2 = conn.cursor()
    user = signedInUser
    like_list = []

    name = ""
    name_table = ""

    for j in c.execute('SELECT username FROM users'):
        name_table = str(j)[3:-3] + "_likes"
        name = str(j)[3:-3]
        for n in c2.execute('SELECT likes FROM ' + name_table):
            if str(n)[3:-3] == "jcr_licklider":
                like_list.append(name)

    return like_list

@app.route("/remove_share", methods=['POST'])
def removeshare(): #Remove share resulting from HTML form value
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    user = signedInUser
    share = str(request.form['share'])
    table_name = "shared_with_" + user

    c.execute('DELETE FROM ' + '"' + table_name + '"' + ' WHERE shares=?', (share,))

    conn.commit()
    conn.close()
    return myshares()

@app.route("/share_steve", methods=['POST'])
def sharesteve(): #The four share methods insert the respective page into the correct user's shared_with~ table, along with the user who shared it
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    c2 = conn.cursor()
    user = signedInUser
    share_name = str(request.form['user'])
    table_name = "shared_with_" + share_name
    already_shared = 0
    sharedBy = ""

    if share_name == "": #Return no response if the HTML form selection was "Select user:"
        return ('', 204)

    for i in c.execute('SELECT shares FROM ' + table_name): #If the respective page has already been shared with this user, do not add it as a separate entry
        if "steve_jobs" in i:
            already_shared = 1

    if already_shared == 0: #If not already shared, add respective page to shares column and logged in user to shared_by column
        c.execute('INSERT into ' + '"' + table_name + '"' + ' values (?,?)', ("steve_jobs", user))
    else:
        for j in c.execute('SELECT shared_by FROM ' + '"' + table_name + '"' + ' WHERE shares=?', ("steve_jobs",)):
            sharedBy = str(j)[3:-3] #If already shared, append logged in user to shared_by column where shares column is the respected page

        c2.execute('UPDATE ' + '"' + table_name + '"' + ' SET shared_by=? WHERE shares=?', (sharedBy + ", " + user, "steve_jobs"))

    conn.commit()
    conn.close()
    return ('', 204)

@app.route("/share_reed", methods=['POST'])
def sharereed():
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    c2 = conn.cursor()
    user = signedInUser
    share_name = str(request.form['user'])
    table_name = "shared_with_" + share_name
    already_shared = 0
    sharedBy = ""

    if share_name == "":
        return ('', 204)

    for i in c.execute('SELECT shares FROM ' + table_name):
        if "reed_hastings" in i:
            already_shared = 1

    if already_shared == 0:
        c.execute('INSERT into ' + '"' + table_name + '"' + ' values (?,?)', ("reed_hastings", user))
    else:
        for j in c.execute('SELECT shared_by FROM ' + '"' + table_name + '"' + ' WHERE shares=?', ("reed_hastings",)):
            sharedBy = str(j)[3:-3]

        c2.execute('UPDATE ' + '"' + table_name + '"' + ' SET shared_by=? WHERE shares=?', (sharedBy + ", " + user, "reed_hastings"))

    conn.commit()
    conn.close()
    return ('', 204)

@app.route("/share_mark", methods=['POST'])
def sharemark():
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    c2 = conn.cursor()
    user = signedInUser
    share_name = str(request.form['user'])
    table_name = "shared_with_" + share_name
    already_shared = 0
    sharedBy = ""

    if share_name == "":
        return ('', 204)

    for i in c.execute('SELECT shares FROM ' + table_name):
        if "mark_zuckerberg" in i:
            already_shared = 1

    if already_shared == 0:
        c.execute('INSERT into ' + '"' + table_name + '"' + ' values (?,?)', ("mark_zuckerberg", user))
    else:
        for j in c.execute('SELECT shared_by FROM ' + '"' + table_name + '"' + ' WHERE shares=?', ("mark_zuckerberg",)):
            sharedBy = str(j)[3:-3]

        c2.execute('UPDATE ' + '"' + table_name + '"' + ' SET shared_by=? WHERE shares=?', (sharedBy + ", " + user, "mark_zuckerberg"))

    conn.commit()
    conn.close()
    return ('', 204)

@app.route("/share_jcr", methods=['POST'])
def sharejcr():
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    c2 = conn.cursor()
    user = signedInUser
    share_name = str(request.form['user'])
    table_name = "shared_with_" + share_name
    already_shared = 0
    sharedBy = ""

    if share_name == "":
        return ('', 204)

    for i in c.execute('SELECT shares FROM ' + table_name):
        if "jcr_licklider" in i:
            already_shared = 1

    if already_shared == 0:
        c.execute('INSERT into ' + '"' + table_name + '"' + ' values (?,?)', ("jcr_licklider", user))
    else:
        for j in c.execute('SELECT shared_by FROM ' + '"' + table_name + '"' + ' WHERE shares=?', ("jcr_licklider",)):
            sharedBy = str(j)[3:-3]

        c2.execute('UPDATE ' + '"' + table_name + '"' + ' SET shared_by=? WHERE shares=?', (sharedBy + ", " + user, "jcr_licklider"))

    conn.commit()
    conn.close()
    return ('', 204)

@app.route("/change_password")
def changepassword(): #Navigate to changepassword.html
    user = signedInUser
    global no_match
    pass_mismatch = no_match
    global verif_mismatch
    verif_failed = verif_mismatch
    return render_template('changepassword.html', **locals())

@app.route("/new_password", methods=['POST'])
def newpassword(): #This method modifies a user's password, upon request
    user = signedInUser
    curr_pass = str(request.form['current_password'])
    new_pass = str(request.form['new_password'])
    confirm_pass = str(request.form['confirm_password'])

    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    
    hashPassOld = ""
    hashPassNew = ""
    hashPassNew = hashlib.md5(new_pass).hexdigest() #Hash new password for database storage, if all verificstion passes
    getOldPass = ""
    hashPassOld = hashlib.md5(curr_pass).hexdigest() #Hash entered current password for comparision with value stored in database

    for i in c.execute('SELECT password FROM users WHERE username = ' + '"' + user + '"'): #Get actual current password for user
        getOldPass = str(i)[3:-3]

    if hashPassOld != getOldPass: #If entered current password and actual current password do not match, alert the user.
        global no_match
        no_match = 1
        global verif_mismatch
        verif_mismatch = 0
        return changepassword()
    else:
        if new_pass != confirm_pass: #If new password and its confirmation don't match, alert the user.
            global verif_mismatch
            verif_mismatch = 1
            global no_match
            no_match = 0
            return changepassword()
        else: #If all verifications pass, update stored password for user to hashed new password
            global no_match
            no_match = 0
            global verif_mismatch
            verif_mismatch = 0
            c.execute('''
                UPDATE users
                SET password=? 
                WHERE username=?
            ''', (hashPassNew, user))
    conn.commit()
    conn.close()
    return render_template('setpassword.html', **locals())

@app.route("/comment_steve", methods=['POST'])
def commentsteve(): #The four comment methods add new user comments to each page's table
    user = signedInUser
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    new_comment = str(request.form['new_comment'])

    c.execute('CREATE TABLE IF NOT EXISTS steve_comments (id INTEGER primary key AUTOINCREMENT, comment varchar(1000), comment_by varchar(100))')
    c.execute('INSERT into steve_comments(comment, comment_by) values(?,?)', (new_comment, user))

    conn.commit()
    conn.close()
    return ('', 204)

@app.route("/comment_reed", methods=['POST'])
def commentreed():
    user = signedInUser
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    new_comment = str(request.form['new_comment'])

    c.execute('CREATE TABLE IF NOT EXISTS reed_comments (id INTEGER primary key AUTOINCREMENT, comment varchar(1000), comment_by varchar(100))')
    c.execute('INSERT into reed_comments(comment, comment_by) values(?,?)', (new_comment, user))

    conn.commit()
    conn.close()
    return ('', 204)

@app.route("/comment_mark", methods=['POST'])
def commentmark():
    user = signedInUser
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    new_comment = str(request.form['new_comment'])

    c.execute('CREATE TABLE IF NOT EXISTS mark_comments (id INTEGER primary key AUTOINCREMENT, comment varchar(1000), comment_by varchar(100))')
    c.execute('INSERT into mark_comments(comment, comment_by) values(?,?)', (new_comment, user))

    conn.commit()
    conn.close()
    return ('', 204)

@app.route("/comment_jcr", methods=['POST'])
def commentjcr():
    user = signedInUser
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    new_comment = str(request.form['new_comment'])

    c.execute('CREATE TABLE IF NOT EXISTS jcr_comments (id INTEGER primary key AUTOINCREMENT, comment varchar(1000), comment_by varchar(100))')
    c.execute('INSERT into jcr_comments(comment, comment_by) values(?,?)', (new_comment, user))

    conn.commit()
    conn.close()
    return ('', 204)

@app.route("/create_page")
def createpage(): # The createpage() method 
    if not session.get('logged_in'): #Restricts access if not logged in
        return render_template("login.html", **locals())
    user = signedInUser
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    table_name = user + "_page"

    #Used as defualt form values
    for i in c.execute('SELECT title FROM ' + table_name):
        title_start = str(i)[3:-3]
    for i in c.execute('SELECT h2_1 FROM ' + table_name):
        h2_1_start = str(i)[3:-3]
    for i in c.execute('SELECT h2_2 FROM ' + table_name):
        h2_2_start = str(i)[3:-3]
    for i in c.execute('SELECT h2_3 FROM ' + table_name):
        h2_3_start = str(i)[3:-3]
    for i in c.execute('SELECT h3_1 FROM ' + table_name):
        h3_1_start = str(i)[3:-3]
    for i in c.execute('SELECT h3_2 FROM ' + table_name):
        h3_2_start = str(i)[3:-3]
    for i in c.execute('SELECT h3_3 FROM ' + table_name):
        h3_3_start = str(i)[3:-3]
    for i in c.execute('SELECT h3_4 FROM ' + table_name):
        h3_4_start = str(i)[3:-3]
    for i in c.execute('SELECT h3_5 FROM ' + table_name):
        h3_5_start = str(i)[3:-3]
    for i in c.execute('SELECT h3_6 FROM ' + table_name):
        h3_6_start = str(i)[3:-3]
    for i in c.execute('SELECT h3_7 FROM ' + table_name):
        h3_7_start = str(i)[3:-3]
    for i in c.execute('SELECT p1 FROM ' + table_name):
        p1_start = str(i)[3:-3]
    for i in c.execute('SELECT p2 FROM ' + table_name):
        p2_start = str(i)[3:-3]
    for i in c.execute('SELECT p3 FROM ' + table_name):
        p3_start = str(i)[3:-3]
    for i in c.execute('SELECT p4 FROM ' + table_name):
        p4_start = str(i)[3:-3]
    for i in c.execute('SELECT p5 FROM ' + table_name):
        p5_start = str(i)[3:-3]
    for i in c.execute('SELECT p6 FROM ' + table_name):
        p6_start = str(i)[3:-3]
    for i in c.execute('SELECT p7 FROM ' + table_name):
        p7_start = str(i)[3:-3]
    for i in c.execute('SELECT p8 FROM ' + table_name):
        p8_start = str(i)[3:-3]
    for i in c.execute('SELECT p9 FROM ' + table_name):
        p9_start = str(i)[3:-3]
    for i in c.execute('SELECT p10 FROM ' + table_name):
        p10_start = str(i)[3:-3]
    for i in c.execute('SELECT p11 FROM ' + table_name):
        p11_start = str(i)[3:-3]
    for i in c.execute('SELECT p12 FROM ' + table_name):
        p12_start = str(i)[3:-3]
    for i in c.execute('SELECT p13 FROM ' + table_name):
        p13_start = str(i)[3:-3]
    for i in c.execute('SELECT p14 FROM ' + table_name):
        p14_start = str(i)[3:-3]
    for i in c.execute('SELECT p15 FROM ' + table_name):
        p15_start = str(i)[3:-3]
    for i in c.execute('SELECT cap1 FROM ' + table_name):
        cap1_start = str(i)[3:-3]
    for i in c.execute('SELECT cap2 FROM ' + table_name):
        cap2_start = str(i)[3:-3]
    for i in c.execute('SELECT cap3 FROM ' + table_name):
        cap3_start = str(i)[3:-3]
    for i in c.execute('SELECT cap4 FROM ' + table_name):
        cap4_start = str(i)[3:-3]
    for i in c.execute('SELECT cap5 FROM ' + table_name):
        cap5_start = str(i)[3:-3]

    #Used to update and print page after editing
    for i in c.execute('SELECT title FROM ' + table_name):
        title = str(i)[3:-3]
    for i in c.execute('SELECT h2_1 FROM ' + table_name):
        h2_1 = str(i)[3:-3]
    for i in c.execute('SELECT h2_2 FROM ' + table_name):
        h2_2 = str(i)[3:-3]
    for i in c.execute('SELECT h2_3 FROM ' + table_name):
        h2_3 = str(i)[3:-3]
    for i in c.execute('SELECT h3_1 FROM ' + table_name):
        h3_1 = str(i)[3:-3]
    for i in c.execute('SELECT h3_2 FROM ' + table_name):
        h3_2 = str(i)[3:-3]
    for i in c.execute('SELECT h3_3 FROM ' + table_name):
        h3_3 = str(i)[3:-3]
    for i in c.execute('SELECT h3_4 FROM ' + table_name):
        h3_4 = str(i)[3:-3]
    for i in c.execute('SELECT h3_5 FROM ' + table_name):
        h3_5 = str(i)[3:-3]
    for i in c.execute('SELECT h3_6 FROM ' + table_name):
        h3_6 = str(i)[3:-3]
    for i in c.execute('SELECT h3_7 FROM ' + table_name):
        h3_7 = str(i)[3:-3]
    for i in c.execute('SELECT p1 FROM ' + table_name):
        p1 = str(i)[3:-3]
    for i in c.execute('SELECT p2 FROM ' + table_name):
        p2 = str(i)[3:-3]
    for i in c.execute('SELECT p3 FROM ' + table_name):
        p3 = str(i)[3:-3]
    for i in c.execute('SELECT p4 FROM ' + table_name):
        p4 = str(i)[3:-3]
    for i in c.execute('SELECT p5 FROM ' + table_name):
        p5 = str(i)[3:-3]
    for i in c.execute('SELECT p6 FROM ' + table_name):
        p6 = str(i)[3:-3]
    for i in c.execute('SELECT p7 FROM ' + table_name):
        p7 = str(i)[3:-3]
    for i in c.execute('SELECT p8 FROM ' + table_name):
        p8 = str(i)[3:-3]
    for i in c.execute('SELECT p9 FROM ' + table_name):
        p9 = str(i)[3:-3]
    for i in c.execute('SELECT p10 FROM ' + table_name):
        p10 = str(i)[3:-3]
    for i in c.execute('SELECT p11 FROM ' + table_name):
        p11 = str(i)[3:-3]
    for i in c.execute('SELECT p12 FROM ' + table_name):
        p12 = str(i)[3:-3]
    for i in c.execute('SELECT p13 FROM ' + table_name):
        p13 = str(i)[3:-3]
    for i in c.execute('SELECT p14 FROM ' + table_name):
        p14 = str(i)[3:-3]
    for i in c.execute('SELECT p15 FROM ' + table_name):
        p15 = str(i)[3:-3]
    for i in c.execute('SELECT cap1 FROM ' + table_name):
        cap1 = str(i)[3:-3]
    for i in c.execute('SELECT cap2 FROM ' + table_name):
        cap2 = str(i)[3:-3]
    for i in c.execute('SELECT cap3 FROM ' + table_name):
        cap3 = str(i)[3:-3]
    for i in c.execute('SELECT cap4 FROM ' + table_name):
        cap4 = str(i)[3:-3]
    for i in c.execute('SELECT cap5 FROM ' + table_name):
        cap5 = str(i)[3:-3]
    return render_template("createpage.html", **locals())

@app.route("/creating_page", methods=['POST'])
def creatingpage():
    user = signedInUser
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    table_name = user + "_page"

    #Same as above
    for i in c.execute('SELECT title FROM ' + table_name):
        title_start = str(i)[3:-3]
    for i in c.execute('SELECT h2_1 FROM ' + table_name):
        h2_1_start = str(i)[3:-3]
    for i in c.execute('SELECT h2_2 FROM ' + table_name):
        h2_2_start = str(i)[3:-3]
    for i in c.execute('SELECT h2_3 FROM ' + table_name):
        h2_3_start = str(i)[3:-3]
    for i in c.execute('SELECT h3_1 FROM ' + table_name):
        h3_1_start = str(i)[3:-3]
    for i in c.execute('SELECT h3_2 FROM ' + table_name):
        h3_2_start = str(i)[3:-3]
    for i in c.execute('SELECT h3_3 FROM ' + table_name):
        h3_3_start = str(i)[3:-3]
    for i in c.execute('SELECT h3_4 FROM ' + table_name):
        h3_4_start = str(i)[3:-3]
    for i in c.execute('SELECT h3_5 FROM ' + table_name):
        h3_5_start = str(i)[3:-3]
    for i in c.execute('SELECT h3_6 FROM ' + table_name):
        h3_6_start = str(i)[3:-3]
    for i in c.execute('SELECT h3_7 FROM ' + table_name):
        h3_7_start = str(i)[3:-3]
    for i in c.execute('SELECT p1 FROM ' + table_name):
        p1_start = str(i)[3:-3]
    for i in c.execute('SELECT p2 FROM ' + table_name):
        p2_start = str(i)[3:-3]
    for i in c.execute('SELECT p3 FROM ' + table_name):
        p3_start = str(i)[3:-3]
    for i in c.execute('SELECT p4 FROM ' + table_name):
        p4_start = str(i)[3:-3]
    for i in c.execute('SELECT p5 FROM ' + table_name):
        p5_start = str(i)[3:-3]
    for i in c.execute('SELECT p6 FROM ' + table_name):
        p6_start = str(i)[3:-3]
    for i in c.execute('SELECT p7 FROM ' + table_name):
        p7_start = str(i)[3:-3]
    for i in c.execute('SELECT p8 FROM ' + table_name):
        p8_start = str(i)[3:-3]
    for i in c.execute('SELECT p9 FROM ' + table_name):
        p9_start = str(i)[3:-3]
    for i in c.execute('SELECT p10 FROM ' + table_name):
        p10_start = str(i)[3:-3]
    for i in c.execute('SELECT p11 FROM ' + table_name):
        p11_start = str(i)[3:-3]
    for i in c.execute('SELECT p12 FROM ' + table_name):
        p12_start = str(i)[3:-3]
    for i in c.execute('SELECT p13 FROM ' + table_name):
        p13_start = str(i)[3:-3]
    for i in c.execute('SELECT p14 FROM ' + table_name):
        p14_start = str(i)[3:-3]
    for i in c.execute('SELECT p15 FROM ' + table_name):
        p15_start = str(i)[3:-3]
    for i in c.execute('SELECT cap1 FROM ' + table_name):
        cap1_start = str(i)[3:-3]
    for i in c.execute('SELECT cap2 FROM ' + table_name):
        cap2_start = str(i)[3:-3]
    for i in c.execute('SELECT cap3 FROM ' + table_name):
        cap3_start = str(i)[3:-3]
    for i in c.execute('SELECT cap4 FROM ' + table_name):
        cap4_start = str(i)[3:-3]
    for i in c.execute('SELECT cap5 FROM ' + table_name):
        cap5_start = str(i)[3:-3]

    #Retrieve from form, then update database
    title = str(request.form['title'])
    h2_1 = str(request.form['h2_1'])
    h2_2 = str(request.form['h2_2'])
    h2_3 = str(request.form['h2_3'])
    h3_1 = str(request.form['h3_1'])
    h3_2 = str(request.form['h3_2'])
    h3_3 = str(request.form['h3_3'])
    h3_4 = str(request.form['h3_4'])
    h3_5 = str(request.form['h3_5'])
    h3_6 = str(request.form['h3_6'])
    h3_7 = str(request.form['h3_7'])
    p1 = str(request.form['p1'])
    p2 = str(request.form['p2'])
    p3 = str(request.form['p3'])
    p4 = str(request.form['p4'])
    p5 = str(request.form['p5'])
    p6 = str(request.form['p6'])
    p7 = str(request.form['p7'])
    p8 = str(request.form['p8'])
    p9 = str(request.form['p9'])
    p10 = str(request.form['p10'])
    p11 = str(request.form['p11'])
    p12 = str(request.form['p12'])
    p13 = str(request.form['p13'])
    p14 = str(request.form['p14'])
    p15 = str(request.form['p15'])
    cap1 = str(request.form['cap1'])
    cap2 = str(request.form['cap2'])
    cap3 = str(request.form['cap3'])
    cap4 = str(request.form['cap4'])
    cap5 = str(request.form['cap5'])

    c.execute('UPDATE ' + '"' + table_name + '"' + '''
        SET title=?, h2_1=?, h2_2=?, h2_3=?,
        h3_1=?, h3_2=?, h3_3=?, h3_4=?, h3_5=?,
        h3_6=?, h3_7=?, p1=?, p2=?, p3=?, p4=?,
        p5=?, p6=?, p7=?, p8=?, p9=?, p10=?,
        p11=?, p12=?, p13=?, p14=?, p15=?,
        cap1=?, cap2=?, cap3=?, cap4=?, cap5=?
        WHERE id=?
    ''', (title, h2_1, h2_2, h2_3, h3_1, h3_2, h3_3,
    h3_4, h3_5, h3_6, h3_7, p1, p2, p3, p4, p5, p6, p7,
    p8, p9, p10, p11, p12, p13, p14, p15, cap1, cap2,
    cap3, cap4, cap5, 1))

    conn.commit()
    conn.close()
    return render_template("createpage.html", **locals())

@app.route("/view_page", methods=['GET','POST'])
def viewpage(): #This method posts the correct user's content into the viewpage.html file
    user = signedInUser
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()

    liked = 0
    table_name2 = user + "_likes"
    like_list = []
    user_page = str(request.form['user']) #Identify selected user's page

    for i in c.execute('SELECT likes FROM ' + table_name2):
        like_list.append(str(i)[3:-3])

    if user_page in like_list:
        liked = 1;

    if str(request.form['user']) == "":
        return ("", 204)

    table_name = str(request.form['user']) + "_page"

    user_list = []
    for i in c.execute('SELECT username FROM users'):
        user_list.append(str(i)[3:-3])

    #Retrieve data from database for printing on page
    for i in c.execute('SELECT title FROM ' + table_name):
        title = str(i)[3:-3]
    for i in c.execute('SELECT h2_1 FROM ' + table_name):
        h2_1 = str(i)[3:-3]
    for i in c.execute('SELECT h2_2 FROM ' + table_name):
        h2_2 = str(i)[3:-3]
    for i in c.execute('SELECT h2_3 FROM ' + table_name):
        h2_3 = str(i)[3:-3]
    for i in c.execute('SELECT h3_1 FROM ' + table_name):
        h3_1 = str(i)[3:-3]
    for i in c.execute('SELECT h3_2 FROM ' + table_name):
        h3_2 = str(i)[3:-3]
    for i in c.execute('SELECT h3_3 FROM ' + table_name):
        h3_3 = str(i)[3:-3]
    for i in c.execute('SELECT h3_4 FROM ' + table_name):
        h3_4 = str(i)[3:-3]
    for i in c.execute('SELECT h3_5 FROM ' + table_name):
        h3_5 = str(i)[3:-3]
    for i in c.execute('SELECT h3_6 FROM ' + table_name):
        h3_6 = str(i)[3:-3]
    for i in c.execute('SELECT h3_7 FROM ' + table_name):
        h3_7 = str(i)[3:-3]
    for i in c.execute('SELECT p1 FROM ' + table_name):
        p1 = str(i)[3:-3]
    for i in c.execute('SELECT p2 FROM ' + table_name):
        p2 = str(i)[3:-3]
    for i in c.execute('SELECT p3 FROM ' + table_name):
        p3 = str(i)[3:-3]
    for i in c.execute('SELECT p4 FROM ' + table_name):
        p4 = str(i)[3:-3]
    for i in c.execute('SELECT p5 FROM ' + table_name):
        p5 = str(i)[3:-3]
    for i in c.execute('SELECT p6 FROM ' + table_name):
        p6 = str(i)[3:-3]
    for i in c.execute('SELECT p7 FROM ' + table_name):
        p7 = str(i)[3:-3]
    for i in c.execute('SELECT p8 FROM ' + table_name):
        p8 = str(i)[3:-3]
    for i in c.execute('SELECT p9 FROM ' + table_name):
        p9 = str(i)[3:-3]
    for i in c.execute('SELECT p10 FROM ' + table_name):
        p10 = str(i)[3:-3]
    for i in c.execute('SELECT p11 FROM ' + table_name):
        p11 = str(i)[3:-3]
    for i in c.execute('SELECT p12 FROM ' + table_name):
        p12 = str(i)[3:-3]
    for i in c.execute('SELECT p13 FROM ' + table_name):
        p13 = str(i)[3:-3]
    for i in c.execute('SELECT p14 FROM ' + table_name):
        p14 = str(i)[3:-3]
    for i in c.execute('SELECT p15 FROM ' + table_name):
        p15 = str(i)[3:-3]
    for i in c.execute('SELECT cap1 FROM ' + table_name):
        cap1 = str(i)[3:-3]
    for i in c.execute('SELECT cap2 FROM ' + table_name):
        cap2 = str(i)[3:-3]
    for i in c.execute('SELECT cap3 FROM ' + table_name):
        cap3 = str(i)[3:-3]
    for i in c.execute('SELECT cap4 FROM ' + table_name):
        cap4 = str(i)[3:-3]
    for i in c.execute('SELECT cap5 FROM ' + table_name):
        cap5 = str(i)[3:-3]

    comment_list = [] #Same comment system as pre-existing pages
    comment_by_list = []
    comment_id = 1
    comment_list_temp =[]
    user_page_comments = str(request.form['user']) + "_page_comments"

    for i in c.execute('SELECT comment FROM ' + user_page_comments):
        comment_list_temp.append(str(i)[3:-3])

    while comment_id <= len(comment_list_temp):
        for i in c.execute('SELECT comment FROM ' + '"' + user_page_comments + '"' + ' WHERE id=?', (comment_id,)):
            comment_list.append(str(i)[3:-3])
        comment_id = comment_id + 1

    comment_id = 1;
    while comment_id <= len(comment_list_temp):
        for i in c.execute('SELECT comment_by FROM ' + '"' + user_page_comments + '"' + ' WHERE id=?', (comment_id,)):
            comment_by_list.append(str(i)[3:-3])
        comment_id = comment_id + 1

    return render_template("viewpage.html", **locals())

@app.route("/share_user_page", methods=['POST'])
def shareuserpage(): #Same sharing system as pre-existing pages
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    c2 = conn.cursor()
    user = signedInUser
    page_name = str(request.form['user_page']) + "'s Page"
    share_name = str(request.form['user'])
    table_name = "user_pages_shared_with_" + share_name
    already_shared = 0
    sharedBy = ""

    if share_name == "": #Return no response if the HTML form selection was "Select user:"
        return ('', 204)

    for i in c.execute('SELECT shares FROM ' + table_name): #If the respective page has already been shared with this user, do not add it as a separate entry
        if page_name in i:
            already_shared = 1

    if already_shared == 0: #If not already shared, add respective page to shares column and logged in user to shared_by column
        c.execute('INSERT into ' + '"' + table_name + '"' + ' values (?,?)', (page_name, user))
    else:
        for j in c.execute('SELECT shared_by FROM ' + '"' + table_name + '"' + ' WHERE shares=?', (page_name,)):
            sharedBy = str(j)[3:-3] #If already shared, append logged in user to shared_by column where shares column is the respected page

        c2.execute('UPDATE ' + '"' + table_name + '"' + ' SET shared_by=? WHERE shares=?', (sharedBy + ", " + user, page_name))

    conn.commit()
    conn.close()
    return ('', 204)

@app.route("/comment_page", methods=['POST'])
def commentpage(): #Same system
    user = signedInUser
    conn = sqlite3.connect('accounts.db')
    c = conn.cursor()
    new_comment = str(request.form['new_comment'])
    user_page_comments = str(request.form['user_page']) + "_page_comments"

    c.execute('INSERT into ' + '"' + user_page_comments + '"' + '(comment, comment_by) values(?,?)', (new_comment, user))

    conn.commit()
    conn.close()
    return ('', 204)

if __name__ == "__main__":
    app.secret_key = os.urandom(12)
    app.run(debug=True,host='0.0.0.0', port=4000) #Run server on localhost:4000



















