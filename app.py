from flask import Flask, render_template, request, jsonify
import bcrypt

app = Flask(__name__)
users = {}

@app.route('/')
def home():
    return render_template('login.html')

@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'GET':
        return render_template('register.html')

    data = request.get_json()
    username = data['username']
    password = data['password']

    if username in users:
        return jsonify({"error": "Username already exists"}), 400

    hashed_pw = bcrypt.hashpw(password.encode('utf-8'), bcrypt.gensalt())
    print("===================================")
    print(f"🆕 New user registered: {username}")
    print(f"🔑 Plain password: {password}")
    print(f"🔒 Hashed password: {hashed_pw}")
    print("===================================")

    users[username] = hashed_pw
    return jsonify({"message": "✅ Account created successfully!"})

@app.route('/login', methods=['POST'])
def login():
    data = request.get_json()
    username = data['username']
    password = data['password']

    if username not in users:
        return jsonify({"error": "❌ User not found"}), 404

    hashed_pw = users[username]
    print("-----------------------------------")
    print(f"👤 User logging in: {username}")
    print(f"🔑 Entered password: {password}")
    print(f"🧮 Stored hash: {hashed_pw}")
    print("-----------------------------------")

    if bcrypt.checkpw(password.encode('utf-8'), hashed_pw):
        print(f"✅ Password match for user '{username}'")
        return jsonify({"message": f"✅ Welcome back, {username}!"})
    else:
        print(f"❌ Incorrect password for user '{username}'")
        return jsonify({"error": "❌ Invalid password"}), 401

@app.route('/forgot', methods=['GET', 'POST'])
def forgot():
    if request.method == 'GET':
        return render_template('forgot.html')

    data = request.get_json()
    username = data['username']

    if username not in users:
        return jsonify({"error": "User not found"}), 404

    return jsonify({"message": "🔗 Password reset link sent (demo only)."})

if __name__ == '__main__':
    app.run(debug=True)
