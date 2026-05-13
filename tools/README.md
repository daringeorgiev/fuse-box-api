# Tools

Admin utility scripts for fuse-box-api.

## Setup

```bash
cd tools
npm install
cp .env.example .env
```

The `.env` file only needs the path to the Firebase service account key:

```env
GOOGLE_APPLICATION_CREDENTIALS=../service-account.json
```

> `service-account.json` is already in the repo root. Download it from **Firebase Console → Project Settings → Service Accounts → Generate new private key** if it is missing.

## Scripts

### set-admin

Grants admin privileges to a Firebase user by email.

```bash
npm run set-admin -- <email>
```

**Example:**

```bash
npm run set-admin -- john.doe@example.com
```

**Expected output:**

```
Success: john.doe@example.com (uid: abc123...) is now an admin
```

> The user's ID token must be refreshed before the claim takes effect. This happens automatically within 1 hour, or you can force it with `getIdToken(true)` on the client.
