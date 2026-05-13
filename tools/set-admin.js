require('dotenv').config();
const admin = require('firebase-admin');
const path = require('path');

const keyPath = process.env.GOOGLE_APPLICATION_CREDENTIALS;
const email = process.argv[2];

if (!keyPath) {
  console.error('Missing GOOGLE_APPLICATION_CREDENTIALS in .env');
  process.exit(1);
}

if (!email) {
  console.error('Usage: npm run set-admin -- <email>');
  process.exit(1);
}

const serviceAccount = require(path.resolve(__dirname, keyPath));

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});

async function setAdmin(targetEmail) {
  try {
    const user = await admin.auth().getUserByEmail(targetEmail);
    await admin.auth().setCustomUserClaims(user.uid, { admin: true });
    console.log(`Success: ${targetEmail} (uid: ${user.uid}) is now an admin`);
  } catch (err) {
    console.error('Error:', err.message);
    process.exit(1);
  } finally {
    process.exit(0);
  }
}

setAdmin(email);
