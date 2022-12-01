use std::fs::File;
use std::io::{self, prelude::*, BufReader};

fn main() -> io::Result<()> {
    let file = File::open("input.txt")?;
    
    let reader = BufReader::new(file);

    let mut elves = Vec::<Elf>::new();
    let mut noms = Vec::<i32>::new();

    for line in reader.lines() {

       let mut line_val = line.unwrap();

       if line_val != "" {
        let mut to_push = line_val.parse::<i32>().unwrap();

        noms.push(to_push);

        println!("Adding {:?} to noms", to_push);

        println!("Noms is{:?}", noms);
            // elves.push(Elf { foodStash: Vec::new() })
       } else {
            // println!("New elf, noms is {:?}", noms);
            elves.push( Elf { foodStash: noms });
       }


        // println!("{}", line?);
    }

    for elf in elves {
        // println!("\r\n\r\n");
        // println!("{:?}", elf.total());
    }


    Ok(())
}

struct Elf {
    foodStash: Vec<i32>
}

impl Elf {
    fn total(&self) -> i32 {
        return self.foodStash.iter().sum::<i32>();
    }
}


