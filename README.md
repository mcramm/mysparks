# mysparks

A web app for tracking ideas. Written in noir.

## Usage

You'll need a postgresql server running and export the following
variable:


```bash
export DATABASE_URL=postgresql://localhost/mysparks
```

You'll also need a database/user.

Then get any dependencies (You will need [Leiningen](https://github.com/technomancy/leiningen)):

```bash
lein deps
```

Then run the migrations (currently a two step process):

```bash
lein run -m mysparks.migrations.create-sparks
lein run -m mysparks.migrations.add-parent-id-to-sparks
```

Now you should be ready to go!

```bash
lein run
```
