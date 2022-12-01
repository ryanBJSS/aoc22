use std::fs::File;
use std::io::{self, prelude::*, BufReader};

fn main() -> io::Result<()> {
    let file = File::open("input.txt")?;

    let reader = BufReader::new(file);

    let mut elves = Vec::<Elf>::new();
    let mut total_calories_for_elf = Vec::<i32>::new();

    for line in reader.lines() {
        let input_line = line.unwrap();

        if input_line != "" {
            let calories_for_food_item = input_line.parse::<i32>().unwrap();
            total_calories_for_elf.push(calories_for_food_item);
        } else {
            elves.push(Elf {
                food_stash: total_calories_for_elf.clone(),
            });
            total_calories_for_elf = Vec::<i32>::new();
        }
    }

    let max = elves.iter().map(|elf| elf.total()).max();

    elves.sort_by(|elf_one, elf_two| elf_two.total().partial_cmp(&elf_one.total()).unwrap()); 

    let top_three_total =  &elves[0..3].iter().map(|elf| elf.total()).sum::<i32>();

    println!("Elf with most calories carrying: {:?}", max.unwrap());

    println!("The top 3 elves with the most calories are collectively carrying: {:?}",top_three_total);

    Ok(())
}

struct Elf {
    food_stash: Vec<i32>,
}

impl Elf {
    fn total(&self) -> i32 {
        return self.food_stash.iter().sum::<i32>();
    }
}
