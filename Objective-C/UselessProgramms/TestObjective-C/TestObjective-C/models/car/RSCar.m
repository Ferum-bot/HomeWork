//
//  RSCar.m
//  TestObjective-C
//
//  Created by Матвей Попов on 17.05.2021.
//

#import "RSCar.h"

@implementation RSCar

@synthesize identifier = _identifier;

- (instancetype)initWithPassenger:(RSPassenger *)captain {
    self = [super initWithPassenger:captain];
    if (self) {
        _identifier = [[NSUUID UUID] UUIDString];
    }
    return self;
}

- (void)move {
    NSLog(@"I'am driven, the driver is %@", self.captain.name);
}



@end
